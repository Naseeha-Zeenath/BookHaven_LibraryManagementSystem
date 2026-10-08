package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookHevanLB_db", "root", "******");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
