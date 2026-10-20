package Controllers.AddBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadCategoryController {

    Connection connection = DBConnection.getInstance().getConnection();

    String SQL = "SELECT * FROM book_categories;";


    private ObservableList<String> getDbCategory(){
        ObservableList<String> categoryList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            while (resultSet.next()){
                String dbAuthorName = resultSet.getString("category_name");
                categoryList.add(dbAuthorName);
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
