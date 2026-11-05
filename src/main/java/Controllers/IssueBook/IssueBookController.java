package Controllers.IssueBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class IssueBookController {

    Connection connection = DBConnection.getInstance().getConnection();

    private ObservableList<String> getDbList(String sql, String idColomn, String nameColumn) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(connection);

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

                if( ! (resultSet.getInt("available_quantity") > 0)){
                        return "This book is not available";
                }


                preparedStatement = connection.prepareStatement(insertSQL);

                preparedStatement.setString(1, Memeber);
                preparedStatement.setString(2, Book);
                preparedStatement.setDate(3, Date.valueOf(IssueDate));
                preparedStatement.setDate(4, Date.valueOf(DueDate));

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success!";
        }
    }
}

