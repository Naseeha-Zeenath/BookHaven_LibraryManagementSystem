package Controllers.AddBook;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class AddBookController {

    Connection connection  = DBConnection.getInstance().getConnection();


    public void InsertBookOnDB(String txtAddNewBook_ISBN, String txtAddNewBookBookTitleText,
                               String txtAddNewBookPublishedYearText, String txtAddNewBookQuantityText,
                               String SelectorAddNewBook_Author, String SelectorAddNewBook_Category){

        String SQL = "INSERT INTO books (isbn,title,published_year,quantity,available_quantity,author_id,category_id)" +
                "VALUES (?,?,?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1,txtAddNewBook_ISBN);
            preparedStatement.setString(2,txtAddNewBookBookTitleText);
            preparedStatement.setString(3,txtAddNewBookPublishedYearText);
            preparedStatement.setString(4,txtAddNewBookQuantityText);
            preparedStatement.setString(5,txtAddNewBookQuantityText);
            preparedStatement.setString(6,SelectorAddNewBook_Author.charAt(0) + "");
            preparedStatement.setString(7,SelectorAddNewBook_Category.charAt(0) + "");

            int executeUpdate = preparedStatement.executeUpdate();
            System.out.println("Insert new book Sucessfully ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
