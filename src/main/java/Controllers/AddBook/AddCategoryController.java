package Controllers.AddBook;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCategoryController {

    Connection connection = DBConnection.getInstance().getConnection();
    public void addCategory(String txtCategoryName, String txtInvalidCategory) {
        String SQL = "INSERT INTO book_categories (category_name) VALUES ('"+txtCategoryName+"');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
