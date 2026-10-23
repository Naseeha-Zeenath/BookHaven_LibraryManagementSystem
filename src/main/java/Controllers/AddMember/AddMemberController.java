package Controllers.AddMember;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMemberController {
    public void registerMember(String txtAddNewMembers_MemberId,
                               String txtAddNewMembers_FullName,
                               String txtAddNewMembers_ContactNumber,
                               String txtAddNewMembers_Email,
                               String txtAddNewMembers_Address) {

        Connection connection = DBConnection.getInstance().getConnection();

        String SQL = "INSERT INTO members(member_id,name,phone,email,address) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

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
}
