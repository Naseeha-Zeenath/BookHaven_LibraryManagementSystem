package Controllers.Login;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    Connection connection = DBConnection.getInstance().getConnection();

    public String checkUserNameAndPassword(String userName, String password) {
        String sql = "SELECT user_name FROM users WHERE user_name = ? AND password = ?";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("user_name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}