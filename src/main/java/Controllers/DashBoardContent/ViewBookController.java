package Controllers.DashBoardContent;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ViewBookController {
    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<Map<String,String>> loadBookData(){

        ObservableList<Map<String,String>> masterBookList = FXCollections.observableArrayList();
        String sql = "select b.book_id,b.isbn,title,a.author_name,bc.category_name,\n" +
                " b.published_year,b.quantity,b.available_quantity \n" +
                " FROM books b INNER JOIN authors a ON b.author_id = a.author_id\n" +
                " INNER JOIN book_categories bc ON b.category_id = bc.category_id;\n";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Map<String, String> book = new HashMap<>();

                book.put("book_id", resultSet.getString("book_id"));
                book.put("isbn", resultSet.getString("isbn"));
                book.put("title", resultSet.getString("title"));
                book.put("author_name", resultSet.getString("author_name"));
                book.put("category_name", resultSet.getString("category_name"));
                book.put("published_year", resultSet.getString("published_year"));
                book.put("quantity", resultSet.getString("quantity"));
                book.put("available_quantity", resultSet.getString("available_quantity"));

                masterBookList.add(book);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  masterBookList;

    }
}
