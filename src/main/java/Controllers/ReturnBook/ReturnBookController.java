package Controllers.ReturnBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReturnBookController {

    Connection connection = DBConnection.getInstance().getConnection();

    public  ObservableList<Map<String, String>> selectDetails(String borrowingId) {
        String sql = "SELECT  m.member_id ,m.email ,m.phone ,bK.isbn " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk ON b.book_id = bk.book_id " +
                "WHERE b.borrowing_id = '"+borrowingId+"'";

        ObservableList<Map<String,String>> returnBookList = FXCollections.observableArrayList();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Map<String, String> returnBookDetails = new HashMap<>();

                returnBookDetails.put("member_id", resultSet.getString("member_id"));
                returnBookDetails.put("email", resultSet.getString("email"));
                returnBookDetails.put("phone", resultSet.getString("phone"));
                returnBookDetails.put("isbn", resultSet.getString("isbn"));

                returnBookList.add(returnBookDetails);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return returnBookList;
    }
}
