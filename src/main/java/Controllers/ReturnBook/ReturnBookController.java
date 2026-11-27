package Controllers.ReturnBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import model.Borrow;
import model.ReturnBookDetails;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReturnBookController {

    Connection connection = DBConnection.getInstance().getConnection();

    public ReturnBookDetails selectDetails(String borrowingId) {

        String sql = "SELECT  m.member_id ,m.email ,m.phone ,bK.isbn " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk ON b.book_id = bk.book_id " +
                "WHERE b.borrowing_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, borrowingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                ReturnBookDetails details = new ReturnBookDetails();

                details.setMemberId(resultSet.getString("member_id"));
                details.setEmail(resultSet.getString("email"));
                details.setPhone(resultSet.getString("phone"));
                details.setIsbn(resultSet.getString("isbn"));

                return details;            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String bookReturn(Borrow borrow){
        String searchSQL = "SELECT issue_date FROM borrowings WHERE borrowing_id = ?";
        String updateSQL = "UPDATE borrowings SET return_date = ? ,status = 'Returned' WHERE borrowing_id =? ";

        if (borrow == null || borrow.getId() == null ||
                borrow.getReturnDate() == null) {

            return "Please complete all required details.";
        }


        try {

            PreparedStatement selectStatement = connection.prepareStatement(searchSQL);
            selectStatement.setInt(1, borrow.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            LocalDate returnDate = borrow.getReturnDate();

            if (resultSet.next()) {
                LocalDate issueDate = resultSet.getDate("issue_date").toLocalDate();

                if (returnDate.isBefore(issueDate)) {
                    return "Return date cannot be before the Borrowed date.";
                }

            }

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setDate(1, Date.valueOf(returnDate));
            preparedStatement.setInt(2,borrow.getId());

            preparedStatement.executeUpdate();

            return "Book returned successfully!";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
