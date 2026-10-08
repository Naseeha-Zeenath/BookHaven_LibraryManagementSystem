package Dashboard;

import db.DBConnection;

import java.sql.*;

public class GetMemberCount {

    Connection connection = DBConnection.getConnection();

    public  String memberCount() {
        String sql = "SELECT COUNT(*) FROM members;";
        int maxRows;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            maxRows = preparedStatement.getMaxRows();
            System.out.println(maxRows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(maxRows);
    }

}
