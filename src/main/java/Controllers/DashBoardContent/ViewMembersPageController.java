package Controllers.DashBoardContent;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

public class ViewMembersPageController implements Initializable {

    ViewMembersController viewMembersController = new ViewMembersController();

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
    private Button btnTotalMembers_PageNext;

    @FXML
    private Button btnTotalMembers_PagePrev;

    @FXML
    private Button btnTotalMembers_Search;

    @FXML
    private TextField txtTotalMembersCount;

    @FXML
    private Label txtTotalMembers_ActiveCount;

    @FXML
    private Label txtTotalMembers_InactiveCount;

    @FXML
    private TextField txtTotalMembers_SearchBar;

    @FXML
    private TextField txtTotalMemebers_PageNumber;

    @FXML
    private TableColumn<Map<String,String>,String> colAddress;

    @FXML
    private TableColumn<Map<String,String>,String> colEmail;

    @FXML
    private TableColumn<Map<String,String>,String> colMemberId;

    @FXML
    private TableColumn<Map<String,String>,String> colName;

    @FXML
    private TableColumn<Map<String,String>,String> colPhone;

    @FXML
    private TableColumn<Map<String,String>,String> colStatus;

    @FXML
    private TableView<Map<String, String>> tblMembers;

    private ObservableList<Map<String, String>> masterMemberList = FXCollections.observableArrayList();
    private FilteredList<Map<String, String>> filteredStudentList;

    private void setMemberTable(){
        colMemberId.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().get("member_id")));
        colName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("name","")));
        colEmail.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("email","")));
        colPhone.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("phone","")));
        colAddress.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("address","")));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("satus","")));

        tblMembers.setItems(masterMemberList);

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
    void btnTotalMembers_SearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnTotalMembers_PageNextOnAction(ActionEvent event) {

    }

    @FXML
    void btnTotalMembers_PagePrevOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        masterMemberList = viewMembersController.loadMemberData();
        setMemberTable();
    }
}
