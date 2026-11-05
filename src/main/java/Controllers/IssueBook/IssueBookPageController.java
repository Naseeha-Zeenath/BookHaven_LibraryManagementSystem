package Controllers.IssueBook;

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


public class IssueBookPageController implements Initializable {

    IssueBookController issueBookController = new IssueBookController();

    @FXML
    private ComboBox<String> SelecterIssueBook_SelectBook;

    @FXML
    private DatePicker SelecterIssueBook_SelectDueDate;

    @FXML
    private DatePicker SelecterIssueBook_SelectIssueDate;

    @FXML
    private ComboBox<String> SelecterIssueBook_SelectMemeber;

    @FXML
    private Button btnIssueBook;

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
    private Label txtInvalidAddIssueBook;

    @FXML
    private TableColumn<Map<String, String>,String>  colBookTitle;

    @FXML
    private TableColumn<Map<String, String>,String> colDueDate;

    @FXML
    private TableColumn<Map<String, String>,String>  colIssueDate;

    @FXML
    private TableColumn<Map<String, String>,String>  colIssueId;

    @FXML
    private TableColumn<Map<String, String>,String>  colMemberName;

    @FXML
    private TableColumn<Map<String, String>,String> colStatus;

    @FXML
    private TableView<Map<String, String>> tblRecentIssues;

    private ObservableList<Map<String, String>> recentIssuesList = FXCollections.observableArrayList();

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {
        txtInvalidAddIssueBook.setText(
                issueBookController.bookIssued(
                        SelecterIssueBook_SelectMemeber.getValue(),
                        SelecterIssueBook_SelectBook.getValue(),
                        SelecterIssueBook_SelectIssueDate.getValue(),
                        SelecterIssueBook_SelectDueDate.getValue()
                )
        );


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SelecterIssueBook_SelectBook.setItems(issueBookController.BookList());
        SelecterIssueBook_SelectMemeber.setItems(issueBookController.MemberList());

        recentIssuesList = issueBookController.loadRecentIssuesData();
        setRecentIssuesTable();
    }

    private void setRecentIssuesTable() {
        colIssueId.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getOrDefault("borrowing_id","")));
        colMemberName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("member_name","")));
        colBookTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("title","")));
        colIssueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("issue_date","")));
        colDueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("due_date","")));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("status","")));

        tblRecentIssues.setItems(recentIssuesList);
    }
}
