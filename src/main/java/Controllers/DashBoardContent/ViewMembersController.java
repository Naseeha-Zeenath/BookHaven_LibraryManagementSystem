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

    public ObservableList<Map<String,String>> loadMemberData(){
        ObservableList<Map<String,String>> masterMemberList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM members;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Map<String, String> member = new HashMap<>();

                member.put("member_id", resultSet.getString("member_id"));
                member.put("name", resultSet.getString("name"));
                member.put("email", resultSet.getString("email"));
                member.put("phone", resultSet.getString("phone"));
                member.put("address", resultSet.getString("address"));
                member.put("satus", resultSet.getString("satus"));

                masterMemberList.add(member);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  masterMemberList;

    }


}