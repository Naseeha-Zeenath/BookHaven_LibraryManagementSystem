package Controllers.AddBook.Author;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AuthorDTO;

public class AddAuthorPageController {

    AddAuthorController addAuthorController = new AddAuthorController();

    @FXML
    private Button btnAddAuthor;

    @FXML
    private Button btnAddAuthorClear;

    @FXML
    private TextField txtAuthorName;

    @FXML
    private Label txtInvalidAuthor;

    @FXML
    void btnAddAuthorClearOnAction(ActionEvent event) {
        txtAuthorName.clear();
        txtInvalidAuthor.setText("");

    }

    @FXML
    void btnAddAuthorOnAction(ActionEvent event) {
        AuthorDTO author = new AuthorDTO();
        author.setName(txtAuthorName.getText());

        if (author.getName().isEmpty()) {
            txtInvalidAuthor.setText("Please enter an Author name...");
        } else if (author.getName().length() > 100) {
            txtInvalidAuthor.setText("Maximum 100 characters allowed...");
        }else {
            txtInvalidAuthor.setText(addAuthorController.addAuther(author));
        }
    }

}
