package Controllers.AddBook.Author;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoadAuthorController {

    Connection connection = DBConnection.getInstance().getConnection();

    String SQL = "SELECT * FROM authors  ORDER BY author_id ASC;";


    private ObservableList<String> getDbAuthor(){
        ObservableList<String> authorList =
                FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println(connection);

            while (resultSet.next()){
                String dbAuthorId = resultSet.getString("author_id");
                String dbAuthorName = resultSet.getString("author_name");

                authorList.add(dbAuthorId+" -"+dbAuthorName);
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
