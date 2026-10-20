package Controllers.AddBook;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBookController {

    Connection connection  = DBConnection.getInstance().getConnection();

    public void InsertBookOnDB(String txtAddNewBook_ISBN, String txtAddNewBookBookTitleText,
                               String txtAddNewBookPublishedYearText,String txtAddNewBookQuantityText){

        String SQL = "INSERT INTO books (isbn,title,published_year,quantity,available_quantity,author_id,category_id)" +
                "VALUES ('"+txtAddNewBook_ISBN+"','"+txtAddNewBookBookTitleText+"'," +
                "'"+txtAddNewBookPublishedYearText+"','"+txtAddNewBookQuantityText+"'," +
                "'"+txtAddNewBookQuantityText+"','1','1');";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            int executeUpdate = preparedStatement.executeUpdate();
            System.out.println(executeUpdate);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
