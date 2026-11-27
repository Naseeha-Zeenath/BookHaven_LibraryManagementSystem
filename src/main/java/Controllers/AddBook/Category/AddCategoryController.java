package Controllers.AddBook.Category;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCategoryController {

    Connection connection = DBConnection.getInstance().getConnection();

    public String addCategory(BookCategory bookCategory) {

            String searchSQL = "SELECT * FROM book_categories WHERE  category_name = '" + bookCategory.getName() + "';";
            String insertSQL = "INSERT INTO book_categories (category_name) VALUES ('" + bookCategory.getName() + "');";

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




    private ObservableList<String> getDbCategory(){
        BookCategory bookCategory = new BookCategory();
        String sql = "SELECT * FROM book_categories ORDER BY category_id ASC;";

        ObservableList<String> categoryList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                bookCategory.setId(Integer.valueOf(resultSet.getString("category_id")));
                bookCategory.setName( resultSet.getString("category_name"));

                categoryList.add(bookCategory.getId()+" -"+bookCategory.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  categoryList;
    }


    public ObservableList<String> category() {
        return getDbCategory();
    }
}
