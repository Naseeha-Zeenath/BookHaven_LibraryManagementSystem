package Controllers.IssueBook;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueBookController {

    Connection connection = DBConnection.getInstance().getConnection();

    private ObservableList<String> getDbList(String sql, String idColomn, String nameColumn) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(connection);

            while (resultSet.next()) {
                String id = resultSet.getString(idColomn);
                String name = resultSet.getString(nameColumn);

                list.add(id + " -" + name);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }



    public ObservableList<String> BookList() {
        String booksLoadSQL = "SELECT * FROM books ORDER BY book_id ASC;";
        return getDbList(booksLoadSQL,"book_id","title");
    }

}

