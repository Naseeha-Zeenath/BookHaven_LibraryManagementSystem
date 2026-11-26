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
import model.Member;

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
    private TextArea txtAddNewMembersAddress;

    @FXML
    private TextField txtAddNewMembersContactNumber;

    @FXML
    private TextField txtAddNewMembersEmail;

    @FXML
    private TextField txtAddNewMembersFullName;

    @FXML
    private TextField txtAddNewMembersMemberId;

    @FXML
    private Button btnGenarateMemberId;


    @FXML
    private Label txtInvalidAddMember;

    @FXML
    void btnGenarateMemberIdOnAction(ActionEvent event) {
        txtAddNewMembersMemberId.setText(addMemberController.generateMemberId());
    }


    @FXML
    void btnLogOutOnAction(ActionEvent event) {
//        ToDo
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
        txtAddNewMembersMemberId.clear();
        txtAddNewMembersFullName.clear();
        txtAddNewMembersContactNumber.clear();
        txtAddNewMembersAddress.clear();
        txtAddNewMembersEmail.clear();
    }

    @FXML
    void btnRegisterMemberOnAction(ActionEvent event) {

        if (txtAddNewMembersMemberId.getText().isEmpty()) {
            txtInvalidAddMember.setText( "To generate a Member ID, click the Generate ID button first.");
        }else if(txtAddNewMembersMemberId.getText().isEmpty() ||
                txtAddNewMembersFullName.getText().isEmpty() ||
                txtAddNewMembersContactNumber.getText().isEmpty() ||
                txtAddNewMembersEmail.getText().isEmpty() ||
                txtAddNewMembersAddress.getText().isEmpty()){
            txtInvalidAddMember.setText( "Please fill in all required fields...");
        } else if (txtAddNewMembersFullName.getLength() >100) {
            txtInvalidAddMember.setText( "Member Name cannot exceed 100 characters...");
        } else if (!txtAddNewMembersContactNumber.getText().matches("^07[0-9]{8}$")) {
            txtInvalidAddMember.setText( "Please enter a valid 10-digit mobile number.");
        } else if (!txtAddNewMembersEmail.getText().matches(".+@.+\\..+")) {
            txtInvalidAddMember.setText( "Please enter a valid email address.");
        }else {
            Member member = new Member();

            member.setId(txtAddNewMembersMemberId.getText());
            member.setName(txtAddNewMembersFullName.getText());
            member.setContactNo(txtAddNewMembersContactNumber.getText());
            member.setEmail(txtAddNewMembersEmail.getText());
            member.setAddress(txtAddNewMembersAddress.getText());

            txtInvalidAddMember.setText(addMemberController.registerMember(member));
        }
    }

}





