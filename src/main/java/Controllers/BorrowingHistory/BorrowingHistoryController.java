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


    private ObservableList<Map<String, String>> loadBorrowedData(String sql, String searchCondition) {

        ObservableList<Map<String,String>> borrowedHistoryList = FXCollections.observableArrayList();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (searchCondition != null) {
                preparedStatement.setString(1, searchCondition);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Map<String, String> brrowedData = new HashMap<>();

                brrowedData.put("borrowing_id", resultSet.getString("borrowing_id"));
                brrowedData.put("member_name", resultSet.getString("member_name"));
                brrowedData.put("title", resultSet.getString("title"));
                brrowedData.put("issue_date", resultSet.getString("issue_date"));
                brrowedData.put("due_date", resultSet.getString("due_date"));
                brrowedData.put("return_date", resultSet.getString("return_date"));
                brrowedData.put("status", resultSet.getString("status"));

                borrowedHistoryList.add(brrowedData);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  borrowedHistoryList;

    }


    private String all() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.return_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id  ORDER BY b.borrowing_id DESC;";
    }
    public ObservableList<Map<String, String>> allCurrentlyBorrowedBookData() {
        return loadBorrowedData(all(), null);
    }



    private String searchMember() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.return_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id WHERE m.member_id = ? ORDER BY b.borrowing_id DESC;";
    }

    public ObservableList<Map<String, String>> searchByMember(String searchMemberId) {
        searchMemberId = searchMemberId.split(" ")[0];
        return loadBorrowedData(searchMember(), searchMemberId);
    }

    private String searchDate() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.return_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id WHERE b.issue_date = ? OR b.due_date =? OR b.return_date =? " +
                "ORDER BY b.borrowing_id DESC;";
    }

    public ObservableList<Map<String, String>> searchByDate(String searchDate) {

        ObservableList<Map<String, String>> borrowedHistoryList = FXCollections.observableArrayList();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(searchDate());

            preparedStatement.setString(1, searchDate);
            preparedStatement.setString(2, searchDate);
            preparedStatement.setString(3, searchDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Map<String, String> borrowedData = new HashMap<>();

                borrowedData.put("borrowing_id", resultSet.getString("borrowing_id"));
                borrowedData.put("member_name", resultSet.getString("member_name"));
                borrowedData.put("title", resultSet.getString("title"));
                borrowedData.put("issue_date", resultSet.getString("issue_date"));
                borrowedData.put("due_date", resultSet.getString("due_date"));
                borrowedData.put("return_date", resultSet.getString("return_date"));
                borrowedData.put("status", resultSet.getString("status"));

                borrowedHistoryList.add(borrowedData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return borrowedHistoryList;
    }
}
