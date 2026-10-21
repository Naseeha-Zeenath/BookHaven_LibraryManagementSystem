package Controllers.AddBook.Author;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddAuthorController {

    Connection connection = DBConnection.getInstance().getConnection();

    public String addAuther(String txtAuthorName) {


        if (txtAuthorName.isEmpty()) {
            return "Please enter an Author name...";
        } else {

            String searchSQL = "SELECT * FROM authors WHERE  author_name =  '" +txtAuthorName+ "';";
            String insertSQL = "INSERT INTO authors (author_name) VALUES ('"+txtAuthorName+"');";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(searchSQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return "This Author name already exists...";
                }
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success!";
        }

    }
}
