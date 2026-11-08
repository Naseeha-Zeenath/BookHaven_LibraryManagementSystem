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

    private ObservableList<Map<String, String>> loadOverdueBookData(String sql, String searchOverdueBook) {

        ObservableList<Map<String, String>> overDueBookList = FXCollections.observableArrayList();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (searchOverdueBook != null) {

                String searchValue = "%" + searchOverdueBook.trim() + "%";

                preparedStatement.setString(1, searchValue);
                preparedStatement.setString(2, searchValue);
                preparedStatement.setString(3, searchValue);
                preparedStatement.setString(4, searchValue);
                preparedStatement.setString(5, searchValue);
                preparedStatement.setString(6, searchValue);

            }

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

        return overDueBookList;

    }

    public ObservableList<Map<String,String>> allOverdueBookData() {
        return loadOverdueBookData(all(), null);
    }

    private String all() {
        return " SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.status," +
                "DATEDIFF(CURDATE(), b.due_date) AS days_overdue\n" +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id " +
                "JOIN books bk ON b.book_id = bk.book_id\n" +
                "WHERE b.due_date < CURDATE() AND b.return_date IS NULL ORDER BY b.due_date DESC;";
    }

    public ObservableList<Map<String,String>> searchOverdueBookData(String searchOverdueBook) {
        return loadOverdueBookData(search(), searchOverdueBook);
    }

    private String search() {
        return "SELECT b.borrowing_id, m.name AS member_name, bk.title, " +
                "b.issue_date, b.due_date, b.status, " +
                "DATEDIFF(CURDATE(), b.due_date) AS days_overdue " +
                "FROM borrowings b " +
                "JOIN members m ON b.member_id = m.member_id " +
                "JOIN books bk ON b.book_id = bk.book_id " +
                "WHERE b.due_date < CURDATE() AND b.return_date IS NULL " +
                "AND (b.borrowing_id LIKE ? " +
                "OR m.name LIKE ? " +
                "OR bk.title LIKE ? " +
                "OR b.issue_date LIKE ? " +
                "OR b.due_date LIKE ? " +
                "OR b.status LIKE ?) " +
                "ORDER BY b.due_date DESC;";
    }

}