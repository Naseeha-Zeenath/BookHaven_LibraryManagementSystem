package Controllers.ManageMember;

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


    public void updateMemberStatus(String memberId) {
        if(memberId != null){
              String SearchSql = "SELECT status FROM members WHERE member_id = '"+memberId+"';";
              String UpdateSql = "UPDATE members SET status = ? WHERE member_id = '"+memberId+"';";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SearchSql);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {

                    String status = resultSet.getString("status");

                    if (status.equals("Active")) {
                        status = "Inactive";
                    } else {
                        status = "Active";
                    }

                    preparedStatement = connection.prepareStatement(UpdateSql);
                    preparedStatement.setString(1, status);
                    preparedStatement.executeUpdate();
                }



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public String updateMemberDetails(String memberId, String name, String phone, String email, String address) {

        if (memberId == null || memberId.isBlank()) {
            return "Member not selected...";
        }else if(name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()){
            return "Please fill in all required fields...";
        } else if (name.length() >100) {
            return "Member Name cannot exceed 100 characters...";
        } else if (!phone.matches("^07[0-9]{8}$")) {
            return "Please enter a valid 10-digit mobile number.";
        } else if (!email.matches(".+@.+\\..+")) {
            return "Please enter a valid email address.";
        }else {
            String sql = "UPDATE members SET name = ?,phone = ?,email = ?,address = ? WHERE member_id = ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, phone);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, memberId);

                preparedStatement.executeUpdate();



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return "Successfully Updated!";
    }
}
