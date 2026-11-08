package Controllers.DashBoardContent;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OverdueBooksController {
    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<Map<String,String>> loadOverdueBookData(){

        ObservableList<Map<String,String>> overDueBookList = FXCollections.observableArrayList();
        String sql = " SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.status," +
                "DATEDIFF(CURDATE(), b.due_date) AS days_overdue\n" +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id " +
                "JOIN books bk ON b.book_id = bk.book_id\n" +
                "WHERE b.due_date < CURDATE() AND b.return_date IS NULL ORDER BY b.due_date DESC;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Map<String, String> OverdueBooks = new HashMap<>();

                OverdueBooks.put("borrowing_id", resultSet.getString("borrowing_id"));
                OverdueBooks.put("member_name", resultSet.getString("member_name"));
                OverdueBooks.put("title", resultSet.getString("title"));
                OverdueBooks.put("issue_date", resultSet.getString("issue_date"));
                OverdueBooks.put("due_date", resultSet.getString("due_date"));
                OverdueBooks.put("days_overdue", resultSet.getString("days_overdue"));
                OverdueBooks.put("status", resultSet.getString("status"));

                overDueBookList.add(OverdueBooks);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  overDueBookList;

    }
}
