package Controllers.ReturnBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.sql.*;
import java.time.LocalDate;
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

    public String bookReturn(String borrowingId, DatePicker returnBookDate){
        String searchSQL = "SELECT issue_date FROM borrowings WHERE borrowing_id = ?";
        String updateSQL = "UPDATE borrowings SET return_date = ? ,status = 'Returned' WHERE borrowing_id =? ";

        try {

            PreparedStatement selectStatement = connection.prepareStatement(searchSQL);
            selectStatement.setString(1, borrowingId);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                LocalDate issueDate = resultSet.getDate("issue_date").toLocalDate();
                LocalDate returnDate = returnBookDate.getValue();

                if (returnDate.isBefore(issueDate)) {
                    return "Return date cannot be before the Borrowed date.";
                }
            }

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setDate(1, Date.valueOf(returnBookDate.getValue()));
            preparedStatement.setString(2,borrowingId);

            preparedStatement.executeUpdate();

            return "Book returned successfully!";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
