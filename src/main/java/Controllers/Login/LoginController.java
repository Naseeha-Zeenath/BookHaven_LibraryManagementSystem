package Controllers.Login;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    Connection connection = DBConnection.getConnection();

    public boolean checkUserNameAndPassword(String userName, String password) {

        String sql = "SELECT * FROM users WHERE user_name = '"+userName+"' AND password = '"+password+"';";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println(resultSet);

            while (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
