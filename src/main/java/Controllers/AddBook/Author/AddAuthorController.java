package Controllers.AddBook.Author;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AddAuthorController {

    Connection connection = DBConnection.getInstance().getConnection();


    public String addAuther(Author author) {


            String searchSQL = "SELECT * FROM authors WHERE  author_name =  '" + author.getName() + "';";
            String insertSQL = "INSERT INTO authors (author_name) VALUES ('"+author.getName()+"');";

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



    private ObservableList<String> getDbAuthor(){
        Author author = new Author();
        String SQL = "SELECT * FROM authors  ORDER BY author_id ASC;";

        ObservableList<String> authorList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                author.setId(Integer.valueOf(resultSet.getString("author_id")));
                author.setName(resultSet.getString("author_name"));

                authorList.add(author.getId()+" -"+author.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  authorList;

    }


    public ObservableList<String> author() {
        return getDbAuthor();
    }

}
