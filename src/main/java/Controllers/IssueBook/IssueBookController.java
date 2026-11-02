package Controllers.IssueBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueBookController {

    Connection connection = DBConnection.getInstance().getConnection();

    String booksLoadSQL = "SELECT * FROM books ORDER BY book_id ASC;";

    private ObservableList<String> getDbBook(){
        ObservableList<String> bookList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(booksLoadSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(connection);

            while (resultSet.next()){
                String dbBookId = resultSet.getString("book_id");
                String dbBookTitle = resultSet.getString("title");

                bookList.add(dbBookId+" -"+dbBookTitle);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  bookList;
    }

    public ObservableList<String> BookList() {

        return getDbBook();
    }

    public ObservableList<String> MemberList() {
        String booksLoadSQL = "SELECT * FROM members ORDER BY member_id ASC;";
        return getDbList(booksLoadSQL,"member_id","name");
    }
}

