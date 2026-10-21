package Controllers.AddBook;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAuthorController {

    Connection connection = DBConnection.getInstance().getConnection();

    public void addAuther(String txtAuthorName, String txtInvalidAuthor) {
        String SQL = "INSERT INTO authors (author_name) VALUES ('"+txtAuthorName+"');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
