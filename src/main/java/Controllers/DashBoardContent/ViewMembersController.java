package Controllers.DashBoardContent;


import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ViewMembersController {
    Connection connection = DBConnection.getInstance().getConnection();

    private ObservableList<Map<String,String>> loadMemberData(String sql, String searchMember){
        ObservableList<Map<String,String>> masterMemberList = FXCollections.observableArrayList();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (searchMember != null) {

                String searchValue = "%" + searchMember.trim() + "%";

                preparedStatement.setString(1, searchValue);
                preparedStatement.setString(2, searchValue);
                preparedStatement.setString(3, searchValue);
                preparedStatement.setString(4, searchValue);
                preparedStatement.setString(5, searchValue);

            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Map<String, String> member = new HashMap<>();

                member.put("member_id", resultSet.getString("member_id"));
                member.put("name", resultSet.getString("name"));
                member.put("email", resultSet.getString("email"));
                member.put("phone", resultSet.getString("phone"));
                member.put("address", resultSet.getString("address"));
                member.put("status", resultSet.getString("status"));

                masterMemberList.add(member);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  masterMemberList;

    }

    public ObservableList<Map<String,String>> searchMemberdata(String searchMember) {
        return loadMemberData(search(), searchMember);
    }

    public ObservableList<Map<String,String>> allloadMemberData(){
        return loadMemberData(all(), null);
    }
    private  String all() {
        return "SELECT * FROM members;";
    }
    private  String search() {
        return "SELECT * FROM members WHERE member_id LIKE ? " +
                "OR name LIKE ? " +
                "OR email LIKE ? " +
                "OR phone LIKE ? " +
                "OR address LIKE ?;";
    }
}