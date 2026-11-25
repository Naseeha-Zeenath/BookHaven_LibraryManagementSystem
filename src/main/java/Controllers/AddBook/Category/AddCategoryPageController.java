package Controllers.AddBook.Category;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.jfr.Category;
import model.Author;
import model.BookCategory;

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
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName(txtCategoryName.getText());

        if (bookCategory.getName().isEmpty()) {
            txtInvalidCategory.setText("Please enter a Category name...");
        } else if (bookCategory.getName().length() > 100) {
            txtInvalidCategory.setText("Maximum 100 characters allowed...");
        }else {
            txtInvalidCategory.setText(addCategoryController.addCategory(bookCategory));
        }
    }

}
