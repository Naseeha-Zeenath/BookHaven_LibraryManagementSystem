package Controllers.BorrowingHistory;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BorrowingHistoryController {

    Connection connection = DBConnection.getInstance().getConnection();

    public String getBorrowingHistoryRecordCount() {
        String sql = "SELECT COUNT(*) AS Count FROM borrowings;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "0";
    }




    private ObservableList<String> getMemberList(){
        String SQL = "SELECT member_id,name FROM members  ORDER BY member_id ASC;";

        ObservableList<String> MemberList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String memberId = resultSet.getString("member_id");
                String memberName = resultSet.getString("name");

                MemberList.add(memberId+" -"+memberName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  MemberList;

    }


    public ObservableList<String> memberList() {
        return getMemberList();
    }


    private ObservableList<Map<String, String>> loadBorrowedData(String sql, String searchBorrowedBook) {

        ObservableList<Map<String,String>> borrowedBookList = FXCollections.observableArrayList();


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

                Map<String, String> brrowedBook = new HashMap<>();

                brrowedBook.put("borrowing_id", resultSet.getString("borrowing_id"));
                brrowedBook.put("member_name", resultSet.getString("member_name"));
                brrowedBook.put("title", resultSet.getString("title"));
                brrowedBook.put("issue_date", resultSet.getString("issue_date"));
                brrowedBook.put("due_date", resultSet.getString("due_date"));
                brrowedBook.put("return_date", resultSet.getString("return_date"));
                brrowedBook.put("status", resultSet.getString("status"));

                borrowedBookList.add(brrowedBook);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  borrowedBookList;

    }


    private String all() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.return_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id  ORDER BY b.borrowing_id DESC;";
    }
    public ObservableList<Map<String, String>> allCurrentlyBorrowedBookData() {
        return loadBorrowedData(all(), null);
    }
}
