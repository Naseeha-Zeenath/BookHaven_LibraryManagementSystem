package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddBookController {

    @FXML
    private SplitMenuButton SelectorAddNewBook_Category;

    @FXML
    private SplitMenuButton SelectorAddNewBook_Status;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnBorrowingHistory;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnManageMembers;

    @FXML
    private Button btnNewBookSave;

    @FXML
    private Button btnReturnBook;

    @FXML
    private TextField txtAddNewBook_Author;

    @FXML
    private TextField txtAddNewBook_BookCopies;

    @FXML
    private TextField txtAddNewBook_BookPublication;

    @FXML
    private TextField txtAddNewBook_BookTitle;

    @FXML
    private TextField txtAddNewBook_CopyrightYear;

    @FXML
    private TextField txtAddNewBook_ISBN;

    @FXML
    private TextField txtAddNewBook_PublisherName;

    @FXML
    void SelectorNewBookCategoryOnAction(ActionEvent event) {

    }

    @FXML
    void SelectorNewBookStatusOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {

    }

    @FXML
    void btnBorrowingHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Home_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnManageMembersOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewBookSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

    }

}
