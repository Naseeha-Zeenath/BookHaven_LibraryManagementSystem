package Controllers.AddBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoadAuthorController {



    Connection connection = DBConnection.getInstance().getConnection();

    String SQL = "SELECT * FROM authors;";


    private ObservableList<String> getDbAuthor(){
        ObservableList<String> authorList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            while (resultSet.next()){
                String dbAuthorName = resultSet.getString("author_name");
                authorList.add(dbAuthorName);
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
