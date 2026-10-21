package Controllers.AddBook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddCategoryPageController {

    AddCategoryController addCategoryController = new AddCategoryController();

    @FXML
    private Button btnAddCategory;

    @FXML
    private Button btnAddCategoryClear;

    @FXML
    private TextField txtCategoryName;

    @FXML
    private Label txtInvalidCategory;

    @FXML
    void btnAddCategoryClearOnAction(ActionEvent event) {
        txtCategoryName.clear();
        txtInvalidCategory.setText("");
    }

    @FXML
    void btnAddCategoryOnAction(ActionEvent event) {
        addCategoryController.addCategory(txtCategoryName.getText(), txtInvalidCategory.getText());
    }

}
