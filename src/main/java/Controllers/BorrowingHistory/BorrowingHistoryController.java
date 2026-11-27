package Controllers.BorrowingHistory;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BorrowingHistory;
import model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        Member member = new Member();

        String SQL = "SELECT member_id,name FROM members  ORDER BY member_id ASC;";

        ObservableList<String> MemberList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                member.setId( resultSet.getString("member_id"));
                member.setName( resultSet.getString("name"));

                MemberList.add(member.getId()+" -"+member.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  MemberList;

    }


    public ObservableList<String> memberList() {
        return getMemberList();
    }



    private String all() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.return_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id  ORDER BY b.borrowing_id DESC;";
    }

    private String searchByMemberAndDate() {
        return "SELECT b.borrowing_id, m.name AS member_name, bk.title, " +
                "b.issue_date, b.due_date, b.return_date, b.status " +
                "FROM borrowings b " +
                "JOIN members m ON b.member_id = m.member_id " +
                "JOIN books bk ON b.book_id = bk.book_id " +
                "WHERE m.member_id = ? " +
                "AND (b.issue_date = ? OR b.due_date = ? OR b.return_date = ?) " +
                "ORDER BY b.borrowing_id DESC;";
    }

    private String searchMember() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.return_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id WHERE m.member_id = ? ORDER BY b.borrowing_id DESC;";
    }

    private String searchDate() {
        return "SELECT b.borrowing_id,m.name AS member_name,bk.title,b.issue_date,b.due_date,b.return_date,b.status " +
                "FROM borrowings b JOIN members m ON b.member_id = m.member_id JOIN books bk " +
                "ON b.book_id = bk.book_id WHERE b.issue_date = ? OR b.due_date =? OR b.return_date =? " +
                "ORDER BY b.borrowing_id DESC;";
    }

    private ObservableList<BorrowingHistory> loadBorrowedData(String sql, String... searchConditions){

        ObservableList<BorrowingHistory> borrowedHistoryList = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 0; i < searchConditions.length; i++) {
                preparedStatement.setString(i + 1, searchConditions[i]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                BorrowingHistory history = new BorrowingHistory();


                history.setBorrowingId(resultSet.getString("borrowing_id"));
                history.setMemberName(resultSet.getString("member_name"));
                history.setBookTitle(resultSet.getString("title"));
                history.setIssueDate(resultSet.getString("issue_date"));
                history.setDueDate(resultSet.getString("due_date"));
                history.setReturnDate(resultSet.getString("return_date"));
                history.setStatus(resultSet.getString("status"));

                borrowedHistoryList.add(history);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return borrowedHistoryList;
    }



    // Load all borrowing records
    public ObservableList<BorrowingHistory> allCurrentlyBorrowedBookData() {
        return loadBorrowedData(all());
    }

    public ObservableList<BorrowingHistory> searchByMember(String searchMemberId) {

        searchMemberId = searchMemberId.split(" ")[0];

        return loadBorrowedData(searchMember(), searchMemberId);
    }

    public ObservableList<BorrowingHistory> searchByDate(String selectedDate) {

        return loadBorrowedData(
                searchDate(),
                selectedDate,
                selectedDate,
                selectedDate
        );
    }


    // Search by member and date
    public ObservableList<BorrowingHistory> searchByMemberAndDate(
            String searchMemberId, String selectedDate) {

        searchMemberId = searchMemberId.split(" ")[0];

        return loadBorrowedData(
                searchByMemberAndDate(),
                searchMemberId,
                selectedDate,
                selectedDate,
                selectedDate
        );
    }

}
