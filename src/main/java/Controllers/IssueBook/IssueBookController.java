package Controllers.IssueBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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


    public ObservableList<String> BookList() {
        String booksLoadSQL = "SELECT * FROM books ORDER BY book_id ASC;";
        return getDbList(booksLoadSQL,"book_id","title");
    }

    public ObservableList<String> MemberList() {
        String booksLoadSQL = "SELECT * FROM members ORDER BY member_id ASC;";
        return getDbList(booksLoadSQL,"member_id","name");
    }



    public String bookIssued(String Memeber, String Book, LocalDate IssueDate, LocalDate DueDate) {

        String insertSQL = "INSERT INTO borrowings (member_id, book_id, issue_date, due_date) VALUES (?, ?, ?, ?);";

        String searchSQL = "SELECT available_quantity FROM books WHERE book_id = ?;";

        String updateSQL = "UPDATE books SET available_quantity = available_quantity - 1 WHERE book_id = ?;";

        if(Memeber == null){
            return "Please select a member";
        } else if (Book == null) {
            return  "Please select a book.";
        } else if (IssueDate == null) {
            return "Please select an issue date.";
        }else if (IssueDate.isAfter(LocalDate.now())) {
            return  "Issue date cannot be in the future.";
        } else if (DueDate == null) {
            return "Please select a due date.";
        } else if (DueDate.isBefore(IssueDate)) {
            return "Due date must be after the issue date.";
        }else {

            Memeber = Memeber.split(" ")[0];
            Book = Book.split(" ")[0];

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(searchSQL);
                preparedStatement.setString(1, Book);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    if (!(resultSet.getInt("available_quantity") > 0)) {
                        return "This book is not available";
                    }
                }


                preparedStatement = connection.prepareStatement(insertSQL);

                preparedStatement.setString(1, Memeber);
                preparedStatement.setString(2, Book);
                preparedStatement.setDate(3, Date.valueOf(IssueDate));
                preparedStatement.setDate(4, Date.valueOf(DueDate));
                preparedStatement.executeUpdate();


                preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setString(1, Book);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success!";
        }
    }

    public ObservableList<Map<String, String>> loadRecentIssuesData() {

        ObservableList<Map<String,String>> recentIssuesList = FXCollections.observableArrayList();
        String sql = "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id ORDER BY b.borrowing_id DESC LIMIT 5;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Map<String, String> recentIssues = new HashMap<>();

                recentIssues.put("borrowing_id", resultSet.getString("borrowing_id"));
                recentIssues.put("member_name", resultSet.getString("member_name"));
                recentIssues.put("title", resultSet.getString("title"));
                recentIssues.put("issue_date", resultSet.getString("issue_date"));
                recentIssues.put("due_date", resultSet.getString("due_date"));
                recentIssues.put("status", resultSet.getString("status"));

                recentIssuesList.add(recentIssues);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  recentIssuesList;

    }
}

