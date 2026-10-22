package Controllers.Dashboard;

import db.DBConnection;

import java.sql.*;

public class GetMemberCount {

    Connection connection = DBConnection.getInstance().getConnection();

    public  String memberCount() {
        String sql = "SELECT COUNT(*) FROM members;";
        int maxRows;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            maxRows = preparedStatement.getMaxRows();
//            System.out.println(maxRows);
//            System.out.println(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(maxRows);
    }

}
