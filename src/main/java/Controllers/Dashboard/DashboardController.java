package Controllers.Dashboard;

import db.DBConnection;

import java.sql.*;

public class DashboardController {

    private final Connection connection = DBConnection.getInstance().getConnection();

    private String getCount(String sql) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "_";
    }

    public  String memberCount() {
        String sql = "SELECT COUNT(*) as count FROM members;";
        return getCount(sql);
    }

    public  String bookCount() {
        String sql = "SELECT COUNT(*) as count FROM books;";
        return getCount(sql);
    }

    public  String activeMemberCount() {
        String sql = "SELECT COUNT(*) as count FROM members WHERE satus = 'Active';";
        return getCount(sql);
    }

    public  String availableBookCount() {
        String sql = "SELECT COUNT(*) as count FROM books WHERE available_quantity > 0;";
        return getCount(sql);
    }

    public  String borrowingBookCount(){
        String sql = "SELECT COUNT(*) AS count FROM borrowings WHERE return_date IS NULL;";
        return getCount(sql);
    }
    public String overdueDateCount(){
        String sql = "SELECT COUNT(*) AS count FROM borrowings WHERE return_date IS NULL AND due_date < CURRENT_DATE();";
        return  getCount(sql);
    }

}
