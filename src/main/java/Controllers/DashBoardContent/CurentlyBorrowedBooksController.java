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

public class CurentlyBorrowedBooksController {

    Connection connection = DBConnection.getInstance().getConnection();

    private ObservableList<Map<String, String>> loadCurrentlyBorrowedData(String sql, String searchBorrowedBook) {

        ObservableList<Map<String,String>> currentlyBorrowedBookList = FXCollections.observableArrayList();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (searchBorrowedBook != null) {

                String searchValue = "%" + searchBorrowedBook.trim() + "%";

                preparedStatement.setString(1, searchValue);
                preparedStatement.setString(2, searchValue);
                preparedStatement.setString(3, searchValue);
                preparedStatement.setString(4, searchValue);
                preparedStatement.setString(5, searchValue);
                preparedStatement.setString(6, searchValue);

            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Map<String, String> currentlyBorrowedBook = new HashMap<>();

                currentlyBorrowedBook.put("borrowing_id", resultSet.getString("borrowing_id"));
                currentlyBorrowedBook.put("member_name", resultSet.getString("member_name"));
                currentlyBorrowedBook.put("title", resultSet.getString("title"));
                currentlyBorrowedBook.put("issue_date", resultSet.getString("issue_date"));
                currentlyBorrowedBook.put("due_date", resultSet.getString("due_date"));
                currentlyBorrowedBook.put("status", resultSet.getString("status"));

                currentlyBorrowedBookList.add(currentlyBorrowedBook);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  currentlyBorrowedBookList;

    }

    public ObservableList<Map<String, String>> allCurrentlyBorrowedBookData() {
        return loadCurrentlyBorrowedData(all(), null);
    }

    private String all() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id WHERE b.status ='Borrowed' ORDER BY b.borrowing_id DESC;";
    }

    public ObservableList<Map<String, String>> searchBookData(String searchBorrowedBook) {
        return loadCurrentlyBorrowedData(search(), searchBorrowedBook);
    }

    private String search() {
        return "SELECT b.borrowing_id, m.name AS member_name, bk.title, " +
                "b.issue_date, b.due_date, b.status " +
                "FROM borrowings b " +
                "JOIN members m ON b.member_id = m.member_id " +
                "JOIN books bk ON b.book_id = bk.book_id " +
                "WHERE b.status = 'Borrowed' " +
                "AND (b.borrowing_id LIKE ? " +
                "OR m.name LIKE ? " +
                "OR bk.title LIKE ? " +
                "OR b.issue_date LIKE ? " +
                "OR b.due_date LIKE ? " +
                "OR b.status LIKE ?) " +
                "ORDER BY b.borrowing_id DESC;";
    }

}
