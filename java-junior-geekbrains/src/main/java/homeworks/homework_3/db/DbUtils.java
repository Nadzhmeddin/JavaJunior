package homeworks.homework_3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private final static String URL = "jdbc:mysql://localhost:3306/persons_db";
    private final static String USER ="root";
    private final static String PASSWORD = "Nadzhmeddin1";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }
        return connection;
    }
}
