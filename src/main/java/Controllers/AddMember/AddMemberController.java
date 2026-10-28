package Controllers.AddMember;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMemberController {
    public String registerMember(String txtAddNewMembers_MemberId,
                               String txtAddNewMembers_FullName,
                               String txtAddNewMembers_ContactNumber,
                               String txtAddNewMembers_Email,
                               String txtAddNewMembers_Address) {

        Connection connection = DBConnection.getInstance().getConnection();

        if (txtAddNewMembers_MemberId.isEmpty()) {
            return "To generate a Member ID, click the Generate ID button first.";
        }else if(txtAddNewMembers_MemberId.isEmpty() ||
                txtAddNewMembers_FullName.isEmpty() ||
                txtAddNewMembers_ContactNumber.isEmpty() ||
                txtAddNewMembers_Email.isEmpty() ||
                txtAddNewMembers_Address.isEmpty()){
            return "Please fill in all required fields...";
        } else if (txtAddNewMembers_FullName.length() >100) {
            return "Member Id cannot exceed 100 characters...";
        } else if (txtAddNewMembers_ContactNumber.matches("^07[0-9]{8}$")) {
            return "Please enter a valid 10-digit mobile number.";
        } else if (!txtAddNewMembers_Email.matches(".+@.+\\..+")) {
            return "Please enter a valid email address.";
        }else {
            String uniqueEmailSearchSQL = "SELECT * FROM members WHERE email = '"+txtAddNewMembers_Email+"';";
            String insertSQL = "INSERT INTO members(member_id,name,phone,email,address) VALUES (?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(uniqueEmailSearchSQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return "This Email address is already registered...";
                }


                preparedStatement = connection.prepareStatement(insertSQL);

                preparedStatement.setString(1,txtAddNewMembers_MemberId);
                preparedStatement.setString(2,txtAddNewMembers_FullName);
                preparedStatement.setString(3,txtAddNewMembers_ContactNumber);
                preparedStatement.setString(4,txtAddNewMembers_Email);
                preparedStatement.setString(5,txtAddNewMembers_Address);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return "Success!";
    }
}
