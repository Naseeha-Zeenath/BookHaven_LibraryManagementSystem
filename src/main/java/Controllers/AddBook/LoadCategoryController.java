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

    String SQL = "SELECT * FROM book_categories ORDER BY category_id ASC;";


    private ObservableList<String> getDbCategory(){
        ObservableList<String> categoryList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(connection);

            while (resultSet.next()){
                String dbCategoryId = resultSet.getString("category_id");
                String dbCategoryName = resultSet.getString("category_name");

                categoryList.add(dbCategoryId+" -"+dbCategoryName);
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
