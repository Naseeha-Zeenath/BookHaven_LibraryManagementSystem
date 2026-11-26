package Controllers.AddBook;

import db.DBConnection;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddBookController {

    Connection connection  = DBConnection.getInstance().getConnection();


    public String InsertBookOnDB(Book book) {

            String ISBNsearchSQL = "SELECT * FROM books WHERE  isbn =  '" + book.getIsbn() + "';";

            String insertSQL = "INSERT INTO books (isbn,title,published_year,quantity,available_quantity,author_id,category_id)" +
                    "VALUES (?,?,?,?,?,?,?);";


            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ISBNsearchSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    return "This ISBN is already registered...";
                }

                preparedStatement = connection.prepareStatement(insertSQL);

                preparedStatement.setString(1, book.getIsbn());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setInt(3, book.getYear());
                preparedStatement.setInt(4, book.getQuantity());
                preparedStatement.setInt(5, book.getAvailableQty());;

                preparedStatement.setInt(6, book.getAuthorId());
                preparedStatement.setInt(7, book.getCategoryId());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success!";

        }


}
