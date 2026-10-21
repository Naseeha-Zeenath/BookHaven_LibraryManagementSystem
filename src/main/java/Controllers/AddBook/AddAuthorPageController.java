package Controllers.AddBook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        addAuthorController.addAuther(txtAuthorName.getText(),txtInvalidAuthor.getText());
    }

}
