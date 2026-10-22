package Controllers.AddBook.Category;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCategoryController {

    Connection connection = DBConnection.getInstance().getConnection();

    public String addCategory(String txtCategoryName) {

        if (txtCategoryName.isEmpty()) {
            return "Please enter a category name...";
        } else {

            String searchSQL = "SELECT * FROM book_categories WHERE  category_name = '" + txtCategoryName + "';";
            String insertSQL = "INSERT INTO book_categories (category_name) VALUES ('" + txtCategoryName + "');";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(searchSQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return "This category name already exists...";
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
