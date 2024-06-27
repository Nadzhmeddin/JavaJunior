package lectures.lecture_3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class DbUtils {

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Nadzhmeddin1";

    public static void getConnection() {

        // JDBC

//        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//            System.out.println("Вы успешно подключились к БД!");
//            Statement statement = connection.createStatement();
//            statement.execute("DROP SCHEMA lecture_3");
//            statement.execute("CREATE SCHEMA IF NOT EXISTS lecture_3");
//            statement.execute("CREATE TABLE IF NOT EXISTS lecture_3.table (id INT NOT NULL, firstname VARCHAR(45) NULL, lastname VARCHAR(45) NULL, PRIMARY KEY(`id`));");
//            statement.execute("INSERT INTO lecture_3.table (id, firstname, lastname) VALUES (1, 'Иванов', 'Иван');");
//            statement.execute("INSERT INTO lecture_3.table (id, firstname, lastname) VALUES (2, 'Петров', 'Петр');");
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM lecture_3.table");
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String firstname = resultSet.getString("firstname");
//                String lastname = resultSet.getString("lastname");
//
//                System.out.printf("Данные пользователя: %s, %s, %s\n", id, firstname, lastname);
//            }
//        } catch (SQLException e) {
//            System.err.println("Ошибка подключения к БД: " + e.getMessage());
//        }


        // Hibernate JPA

        // Создание объекта в БД
//        Connector connector = new Connector();
//        Magic magic = new Magic("Волшебная стрела", 10, 0, 0);
//        session.beginTransaction();
//        session.persist(magic);
//        magic = new Magic("Молния", 25, 0, 0);
//        session.persist(magic);
//        magic = new Magic("Каменная кожа", 0, 0, 6);
//        session.persist(magic);
//        magic = new Magic("Жажда крови", 0, 6, 0);
//        session.persist(magic);
//        magic = new Magic("Проклятие", 0, -3, 0);
//        session.persist(magic);
//        magic = new Magic("Лечение", -30, 0, 0);
//        session.persist(magic);
//        session.getTransaction();
//        session.close();


        // Получение данных из БД
//        Connector connector = new Connector();
//        try(Session session = connector.getSession()) {
//            List<Magic> books = session.createQuery("FROM Magic", Magic.class).getResultList();
//            books.forEach(System.out::println);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Изменение данных в БД
//        Connector connector = new Connector();
//        try(Session session = connector.getSession()) {
//
//            String hql = "from Magic where id = :id";
//            Query<Magic> query = session.createQuery(hql, Magic.class);
//            query.setParameter("id", 4);
//            Magic magic = query.getSingleResult();
//            System.out.println(magic);
//            magic.setAttBonus(100);
//            magic.setName("Ярость");
//            session.beginTransaction();
//            session.persist(magic);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Удаление данных из БД
//        Connector connector = new Connector();
//        try(Session session = connector.getSession()) {
//            Transaction transaction = session.beginTransaction();
//            List<Magic> magics = session.createQuery("FROM Magic", Magic.class).getResultList();
//            magics.forEach(m -> {
//                session.remove(m);
//            });
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
