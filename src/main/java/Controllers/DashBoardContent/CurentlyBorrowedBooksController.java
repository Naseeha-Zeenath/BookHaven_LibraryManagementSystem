package Controllers.DashBoardContent;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BorrowingHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CurentlyBorrowedBooksController {

    Connection connection = DBConnection.getInstance().getConnection();



    private ObservableList<BorrowingHistory> loadCurrentlyBorrowedData(String sql, String searchBorrowedBook) {

        ObservableList<BorrowingHistory> currentlyBorrowedBookList = FXCollections.observableArrayList();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (searchBorrowedBook != null) {

                String searchValue = "%" + searchBorrowedBook.trim() + "%";

                for (int i = 1; i <= 6; i++) {
                    preparedStatement.setString(i, searchValue);
                }

            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BorrowingHistory history = new BorrowingHistory();

                history.setBorrowingId(resultSet.getString("borrowing_id"));
                history.setMemberName(resultSet.getString("member_name"));
                history.setBookTitle(resultSet.getString("title"));
                history.setIssueDate(resultSet.getString("issue_date"));
                history.setDueDate(resultSet.getString("due_date"));
                history.setStatus(resultSet.getString("status"));

                currentlyBorrowedBookList.add(history);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  currentlyBorrowedBookList;
    }



    public ObservableList<BorrowingHistory> allCurrentlyBorrowedBookData() {
        return loadCurrentlyBorrowedData(all(), null);
    }

    private String all() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id WHERE b.status ='Borrowed' ORDER BY b.borrowing_id DESC;";
    }

    public ObservableList<BorrowingHistory> searchBookData(String searchBorrowedBook) {
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
