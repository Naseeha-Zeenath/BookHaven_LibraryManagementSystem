package Controllers;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ManageMemberController {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<Map<String, String>> loadSearchMemberData(String searchValue) {

        ObservableList<Map<String,String>> masterMemberList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM members WHERE member_id LIKE ? " +
                "OR name LIKE ? " +
                "OR email LIKE ? " +
                "OR phone LIKE ? " +
                "OR address LIKE ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (searchValue != null) {

                searchValue = "%" + searchValue.trim() + "%";

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



}
