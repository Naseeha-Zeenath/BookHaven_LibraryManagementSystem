package Controllers.IssueBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Borrow;
import model.BorrowingHistory;

import java.sql.*;

public class IssueBookController {

    Connection connection = DBConnection.getInstance().getConnection();

    private ObservableList<String> getDbList(String sql, String idColomn, String nameColumn) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString(idColomn);
                String name = resultSet.getString(nameColumn);

                list.add(id + " -" + name);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }


    public ObservableList<String> bookList() {
        String booksLoadSQL = "SELECT * FROM books ORDER BY book_id ASC;";
        return getDbList(booksLoadSQL,"book_id","title");
    }

    public ObservableList<String> memberList() {
        String booksLoadSQL = "SELECT * FROM members ORDER BY member_id ASC;";
        return getDbList(booksLoadSQL,"member_id","name");
    }



    public String bookIssued(Borrow borrow) {

        String insertSQL = "INSERT INTO borrowings (member_id, book_id, issue_date, due_date) VALUES (?, ?, ?, ?);";

        String searchSQL = "SELECT available_quantity FROM books WHERE book_id = ?;";

        String updateSQL = "UPDATE books SET available_quantity = available_quantity - 1 WHERE book_id = ?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(searchSQL);
                preparedStatement.setInt(1, borrow.getBookId());
                ResultSet resultSet = preparedStatement.executeQuery();

                if (!resultSet.next()) {
                    return "Book not found.";
                }

                if (resultSet.getInt("available_quantity") <= 0) {
                    return "This book is not available.";
                }

                preparedStatement = connection.prepareStatement(insertSQL);

                preparedStatement.setString(1, borrow.getMemberId());
                preparedStatement.setInt(2, borrow.getBookId());
                preparedStatement.setDate(3, Date.valueOf(borrow.getIssueDate()));
                preparedStatement.setDate(4, Date.valueOf(borrow.getDueDate()));
                preparedStatement.executeUpdate();


                preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setInt(1, borrow.getBookId());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success!";

    }

    public ObservableList<BorrowingHistory> loadRecentIssuesData() {

        ObservableList<BorrowingHistory> recentIssuesList = FXCollections.observableArrayList();

        String sql = "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id ORDER BY b.borrowing_id DESC LIMIT 5;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                BorrowingHistory history = new BorrowingHistory();

                history.setBorrowingId( resultSet.getString("borrowing_id"));
                history.setMemberName(resultSet.getString("member_name"));
                history.setBookTitle( resultSet.getString("title"));
                history.setIssueDate(resultSet.getString("issue_date"));
                history.setDueDate(resultSet.getString("due_date"));
                history.setStatus(resultSet.getString("status"));

                recentIssuesList.add(history);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  recentIssuesList;

    }
}

