package Controllers.AddBook;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddBookController {

    Connection connection  = DBConnection.getInstance().getConnection();


    public String InsertBookOnDB(String txtAddNewBook_ISBN, String txtAddNewBookBookTitleText,
                               String txtAddNewBookPublishedYearText, String txtAddNewBookQuantityText,
                               String SelectorAddNewBook_Author, String SelectorAddNewBook_Category) {

        if (txtAddNewBook_ISBN.isEmpty() ||
                txtAddNewBookBookTitleText.isEmpty() ||
                SelectorAddNewBook_Author == null ||
                SelectorAddNewBook_Category == null ||
                txtAddNewBookPublishedYearText.isEmpty() ||
                txtAddNewBookQuantityText.isEmpty()) {
            return "Please fill in all required fields...";
        }else if (txtAddNewBook_ISBN.length() != 10 && txtAddNewBook_ISBN.length() != 13)  {
            return "ISBN must contain 10 or 13 characters.";
        } else if (txtAddNewBookBookTitleText.length() > 100) {
            return "Book title cannot exceed 100 characters...";
        }

        int publishedYear;
        int quantity;
        try {
            publishedYear = Integer.parseInt((txtAddNewBookPublishedYearText));
            quantity = Integer.parseInt(txtAddNewBookQuantityText);
        } catch (NumberFormatException e) {
            return "Published year and quantity must be valid integers...";
        }

        if (publishedYear < 0 || publishedYear > java.time.Year.now().getValue()) {
            return "Please enter a valid published year...";
        } else if (txtAddNewBookPublishedYearText.length() != 4) {
            return "Published year must contain exactly 4 digits...";
        } else if (quantity < 0) {
            return "Quantity cannot be negative...";
        } else if (txtAddNewBookQuantityText.length() > 4) {
            return "Book quantity is too large...";
        } else {

            String ISBNsearchSQL = "SELECT * FROM books WHERE  isbn =  '" + txtAddNewBook_ISBN + "';";

            String insertSQL = "INSERT INTO books (isbn,title,published_year,quantity,available_quantity,author_id,category_id)" +
                    "VALUES (?,?,?,?,?,?,?);";


            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ISBNsearchSQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return "This ISBN is already registered...";
                }

                
                preparedStatement = connection.prepareStatement(insertSQL);

                preparedStatement.setString(1, txtAddNewBook_ISBN);
                preparedStatement.setString(2, txtAddNewBookBookTitleText);
                preparedStatement.setString(3, txtAddNewBookPublishedYearText);
                preparedStatement.setString(4, txtAddNewBookQuantityText);
                preparedStatement.setString(5, txtAddNewBookQuantityText);

                String authorId = SelectorAddNewBook_Author.split(" ")[0];
                String categoryId = SelectorAddNewBook_Category.split(" ")[0];

                preparedStatement.setString(6, authorId);
                preparedStatement.setString(7, categoryId);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success!";

        }
    }


}
