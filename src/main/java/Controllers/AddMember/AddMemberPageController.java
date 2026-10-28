package Controllers.AddMember;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class AddMemberPageController {

    AddMemberController addMemberController = new AddMemberController();

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnNavAddBook;

    @FXML
    private Button btnNavAddMember;

    @FXML
    private Button btnNavBorrowingHistory;

    @FXML
    private Button btnNavDashboard;

    @FXML
    private Button btnNavIssueBook;

    @FXML
    private Button btnNavManageMembers;

    @FXML
    private Button btnNavReturnBook;

    @FXML
    private Button btnRegisterMember;

    @FXML
    private Button btnRegisterMemberClear;

    @FXML
    private TextArea txtAddNewMembers_Address;

    @FXML
    private TextField txtAddNewMembers_ContactNumber;

    @FXML
    private TextField txtAddNewMembers_Email;

    @FXML
    private TextField txtAddNewMembers_FullName;

    @FXML
    private TextField txtAddNewMembers_MemberId;

    @FXML
    private Button btnGenarateMemberId;


    @FXML
    private Label txtInvalidAddMember;

    @FXML
    void btnGenarateMemberIdOnAction(ActionEvent event) {
        txtAddNewMembers_MemberId.setText(addMemberController.generateStudentId());
    }


    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnNavAddBookOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddBook_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnNavAddMemberOnAction(ActionEvent event) {

    }

    @FXML
    void btnNavBorrowingHistoryOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/BorrowingHistory_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnNavDashboardOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnNavIssueBookOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/IssueBook_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnNavManageMembersOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ManageMembers_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnNavReturnBookOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReturnBook_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnRegisterMemberClearOnAction(ActionEvent event) {
        txtAddNewMembers_MemberId.clear();
        txtAddNewMembers_FullName.clear();
        txtAddNewMembers_ContactNumber.clear();
        txtAddNewMembers_Address.clear();
        txtAddNewMembers_Email.clear();
    }

    @FXML
    void btnRegisterMemberOnAction(ActionEvent event) {
        txtInvalidAddMember.setText(
                addMemberController.registerMember(txtAddNewMembers_MemberId.getText(),
                        txtAddNewMembers_FullName.getText(),
                        txtAddNewMembers_ContactNumber.getText(),
                        txtAddNewMembers_Email.getText(),
                        txtAddNewMembers_Address.getText()
                )

        );
    }

}





