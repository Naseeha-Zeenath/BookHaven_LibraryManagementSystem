package Dashboard;

import db.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetBookCount {
    Connection connection = DBConnection.getConnection();

    public String bookCount() {
        String sql = "SELECT COUNT(*) FROM books;";

        int maxRows;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            maxRows = preparedStatement.getMaxRows();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(maxRows);
    }

}
