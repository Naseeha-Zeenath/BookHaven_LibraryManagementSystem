package Controllers;

import Controllers.Dashboard.DashboardController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ManageMembersPageController implements Initializable {

    ManageMemberController manageMemberController = new ManageMemberController();
    DashboardController dashboardController = new DashboardController();

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnMemberStatus;

    @FXML
    private Button btnMemebersSearch;

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
    private Button btnUpdateMember;

    @FXML
    private TableColumn<Map<String,String>,String> colAddress;

    @FXML
    private TableColumn<Map<String,String>,String> colContactNo;

    @FXML
    private TableColumn<Map<String,String>, String> colEmail;

    @FXML
    private TableColumn<Map<String,String>,String> colId;

    @FXML
    private TableColumn<Map<String ,String>,String> colName;

    @FXML
    private TableColumn<Map<String,String>,String> colStatus;

    @FXML
    private TableView<Map<String,String>> tblManageMember;
    @FXML
    private Label txtInvalidUpdateMember;

    @FXML
    private TextField txtManageMember_MembersSearchBar;

    @FXML
    private Label txtManageMember_TotalMemberCount;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextArea txtUpdateAddress;

    @FXML
    private TextField txtUpdateContactNumber;

    @FXML
    private TextField txtUpdateEmail;

    @FXML
    private TextField txtUpdateFullName;




    private ObservableList<Map<String, String>> masterMemberList = FXCollections.observableArrayList();

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnMemberStatusOnAction(ActionEvent event) {

        manageMemberController.updateMemberStatus(txtMemberId.getText());
        txtInvalidUpdateMember.setText(btnMemberStatus.getText()+" Sucessfully");
    }

    @FXML
    void btnUpdateMemberOnAction(ActionEvent event) {
        txtInvalidUpdateMember.setText(
                manageMemberController.updateMemberDetails(
                        txtMemberId.getText(),
                        txtUpdateFullName.getText(),
                        txtUpdateContactNumber.getText(),
                        txtUpdateEmail.getText(),
                        txtUpdateAddress.getText()
                )
        );

    }

    @FXML
    void btnMemebersSearchOnAction(ActionEvent event) {
        masterMemberList =manageMemberController.loadSearchMemberData(txtManageMember_MembersSearchBar.getText());
        setMemberData();

        tblManageMember.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, Value) -> {
            txtInvalidUpdateMember.setText(null);
            if (Value != null) {
                txtMemberId.setText(Value.get("member_id"));
                txtUpdateFullName.setText(Value.get("name"));
                txtUpdateContactNumber.setText(Value.get("phone"));
                txtUpdateEmail.setText(Value.get("email"));
                txtUpdateAddress.setText(Value.get("address"));

                if (Value.get("status").equals("Active")) {
                    btnMemberStatus.setText("Inactive");
                } else {
                    btnMemberStatus.setText("Active");
                }
            }
        });

    }

    private void setMemberData() {
        colId.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getOrDefault("member_id","")));
        colName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("name","")));
        colEmail.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("email","")));
        colContactNo.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("phone","")));
        colAddress.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("address","")));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("status","")));

        tblManageMember.setItems(masterMemberList);
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddMember_page.fxml"))));
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtManageMember_TotalMemberCount.setText(dashboardController.memberCount());
    }
}
