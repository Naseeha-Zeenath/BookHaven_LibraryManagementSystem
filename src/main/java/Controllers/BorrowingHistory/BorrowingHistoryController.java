package Controllers.BorrowingHistory;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
