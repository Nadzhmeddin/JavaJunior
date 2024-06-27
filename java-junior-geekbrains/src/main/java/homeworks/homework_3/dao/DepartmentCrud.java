package homeworks.homework_3.dao;

import homeworks.homework_3.db.DbUtils;
import homeworks.homework_3.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCrud {
    /**
     * Метод создания таблицы Department
     */
    public static void createDepartmentTable() {
        try(Connection connection = DbUtils.getConnection();
            Statement statement = connection.createStatement()){
            statement.execute(
                    """
                    CREATE TABLE IF NOT EXISTS Department (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name_department VARCHAR(150)
                     )
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void insertDepartment(Department department) {

        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     """
                          INSERT INTO 
                             department (name_department) 
                          VALUES 
                             (?);
                          """)) {

            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно добавлены!");
        } catch (SQLException ex) {
            System.err.println("Ошибка добавления : " + ex.getMessage());
        }
    }

    public static List<Department> getDepartmentData() {
        List<Department> departmentList = new ArrayList<>();

        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM department")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name_department");
                departmentList.add(new Department(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }
}
