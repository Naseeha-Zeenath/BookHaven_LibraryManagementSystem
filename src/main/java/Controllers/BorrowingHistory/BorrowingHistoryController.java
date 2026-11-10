package Controllers.BorrowingHistory;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowingHistoryController {

    Connection connection = DBConnection.getInstance().getConnection();

    public String getBorrowingHistoryRecordCount() {
        String sql = "SELECT COUNT(*) AS Count FROM borrowings;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "0";
    }
}
