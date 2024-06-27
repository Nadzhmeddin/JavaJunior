package homeworks.homework_3.dao;

import homeworks.homework_3.db.DbUtils;
import homeworks.homework_3.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonCrud {
    /**
     * Метод создания таблицы Person
     */
    public static void createPersonTable() {
        try(Connection connection = DbUtils.getConnection();
            Statement statement = connection.createStatement()){
            statement.execute(
                    """
                    CREATE TABLE IF NOT EXISTS Person (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name_person VARCHAR(150),
                    age INT, active BOOLEAN,
                    department_id BIGINT,
                    FOREIGN KEY (department_id) REFERENCES department(id)
                     )
                    """);;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Данный метод позволяет получить все данные с таблицы Person.
     * @return возвращает List<Person> с полями каждого Person указанного в таблице
     */
    public static List<Person> getPersonData() {
        List<Person> personList = new ArrayList<>();

        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                boolean active = resultSet.getBoolean("active");
                long department_id = resultSet.getLong("department_id");

                personList.add(new Person(id, name, age, active, department_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    /**
     * Метод добавление в таблицу Person
     * @param person в метод передаем Person с полями name, age, active, department_id.
     * Примечание: Поле id в Person не передаем, так как в БД он Auto_increment.
     */
    public static void insertPerson(Person person) {

        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     """
                          INSERT INTO 
                             person (name_person, age, active, department_id) 
                          VALUES 
                             (?, ?, ?, ?);
                          """)) {

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setBoolean(3, person.isActive());
            preparedStatement.setLong(4, person.getDepartment_id());
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно добавлены!");
        } catch (SQLException ex) {
            System.err.println("Ошибка добавления : " + ex.getMessage());
        }
    }

    /**
     * Метод обновления данных Person в БД, поиск Person осуществляется через id.
     * @param id - требуемый id Person для осуществления поиска в БД
     * @param name - изменяемый параметр имя Person
     * @param age - изменяемый параметр возраст Person
     * @param active - изменяемый параметр активность Person
     * @param department_id - изменяемый параметр id департамента
     */
    public static void updatePerson(long id, String name, int age, boolean active, long department_id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     """
                          UPDATE person
                              SET name_person = ?, age = ?, active = ?, department_id = ?
                              WHERE id = ?
                          """)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setBoolean(3, active);
            preparedStatement.setLong(4, department_id);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно обновлены!");
        } catch (SQLException ex) {
            System.err.println("Ошибка обновления данных: " + ex.getMessage());
        }
    }

    /**
     * Метод удаления Person по полученному id из базы данных
     * @param id полученный от пользователя id Person
     */
    public static void deletePerson(long id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(" DELETE FROM person WHERE id = ?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Удаление прошло успешно!");
        } catch (SQLException ex) {
            System.err.println("Ошибка удаления данных: " + ex.getMessage());
        }
    }


    /**
     * Метод вывода названия департамента по id Person.
     * @param id получаемый id Person от пользователя
     * @return возвращает строку с именем и названием департамента работника
     */
    public static String getPersonDepartmentName(long id) {
        String infoPerson = null;
        try(Connection connection = DbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                            SELECT name_department, name_person 
                            FROM department, person 
                            WHERE person.department_id = department.id 
                            AND person.id = ?
                        """)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name_department = resultSet.getString("name_department");
                String name_person = resultSet.getString("name_person");
                infoPerson = name_person + ": " + name_department;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return infoPerson;
    }

    /**
     * Метод получения Map<String, String>, где в качестве ключа используется имя Person, а в качестве значения название Department
     * @return возвращаем Map
     */
    public static Map<String, String> getPersonDepartments() {
        Map<String, String> infoPerson = new HashMap<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     """
                        SELECT name_person, name_department
                        FROM person, department
                        WHERE person.id = department.id
                    """
             )) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String person_name = resultSet.getString("name_person");
                String name_department = resultSet.getString("name_department");
                infoPerson.put(person_name, name_department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return infoPerson;
    }

    /**
     * Метод получения Map<String, List<String>>, где в качестве ключа используется наименование department, а в качестве значения
     * используется List имен работников.
     * @return возвращает Map<String, List<String>>
     */
    public static Map<String, List<String>> getDepartmentPersons() {
        Map<String, List<String>> departmentsWithPerson = new HashMap<>();
        List<String> personNames = new ArrayList<>();
        try(Connection connection = DbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                            SELECT name_department, name_person
                            FROM person, department
                            WHERE person.id = department.id
                        """
            )) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name_department = resultSet.getString("name_department");
                String person_name = resultSet.getString("name_person");

                personNames.add(person_name);
                departmentsWithPerson.put(name_department, personNames);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departmentsWithPerson;
    }
}
