package Controllers.AddMember;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddMemberController {
    Connection connection = DBConnection.getInstance().getConnection();
    public String registerMember(String txtAddNewMembers_MemberId,
                               String txtAddNewMembers_FullName,
                               String txtAddNewMembers_ContactNumber,
                               String txtAddNewMembers_Email,
                               String txtAddNewMembers_Address) {

        if (txtAddNewMembers_MemberId.isEmpty()) {
            return "To generate a Member ID, click the Generate ID button first.";
        }else if(txtAddNewMembers_MemberId.isEmpty() ||
                txtAddNewMembers_FullName.isEmpty() ||
                txtAddNewMembers_ContactNumber.isEmpty() ||
                txtAddNewMembers_Email.isEmpty() ||
                txtAddNewMembers_Address.isEmpty()){
            return "Please fill in all required fields...";
        } else if (txtAddNewMembers_FullName.length() >100) {
            return "Member Name cannot exceed 100 characters...";
        } else if (!txtAddNewMembers_ContactNumber.matches("^07[0-9]{8}$")) {
            return "Please enter a valid 10-digit mobile number.";
        } else if (!txtAddNewMembers_Email.matches(".+@.+\\..+")) {
            return "Please enter a valid email address.";
        }else {
            String uniqueEmailSearchSQL = "SELECT email FROM members WHERE email = '"+txtAddNewMembers_Email+"';";
            String uniqueIdSearchSQL = "SELECT member_id FROM members WHERE member_id = '"+txtAddNewMembers_MemberId+"';";
            String insertSQL = "INSERT INTO members(member_id,name,phone,email,address) VALUES (?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(uniqueIdSearchSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    return "This MemberId is already registered please generate a new Id...";
                }

                preparedStatement = connection.prepareStatement(uniqueEmailSearchSQL);
                resultSet = preparedStatement.executeQuery();

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



    public String generateStudentId() {
        String year = String.valueOf(LocalDate.now().getYear())+"-";
        String sql = "SELECT member_id FROM members WHERE member_id LIKE ? ORDER BY member_id DESC LIMIT 1";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,year+"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return year + "0001";
            }else {
                String lastMemberId = resultSet.getString("member_id");

                    String lastNumberStr = lastMemberId.substring(year.length());

                        int nextNumber = Integer.parseInt(lastNumberStr) + 1;

                        if (nextNumber > 9999) {
                            return "Maximum member limit reached for this year.";
                        }

                        return year + String.format("%04d", nextNumber);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
