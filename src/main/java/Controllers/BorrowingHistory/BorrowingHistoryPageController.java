package Controllers.BorrowingHistory;

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
import java.net.MulticastSocket;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class BorrowingHistoryPageController implements Initializable {

    BorrowingHistoryController borrowingHistoryController = new BorrowingHistoryController();

    @FXML
    private DatePicker SelecterBorrowingHistory_FromDate;

    @FXML
    private ComboBox<String> SelecterBorrowingHistory_MemeberID;

    @FXML
    private DatePicker SelecterBorrowingHistory_ToDate;

    @FXML
    private Label TxtTotalRecordsCount;

    @FXML
    private Button btnBorrowingHistorySearch;

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
    private TableColumn<Map<String,String>,String> colBookTitle;

    @FXML
    private TableColumn<Map<String,String>,String> colDueDate;

    @FXML
    private TableColumn<Map<String,String>,String> colIssueDate;

    @FXML
    private TableColumn<Map<String,String>,String> colBorrowingID;

    @FXML
    private TableColumn<Map<String,String>,String> colMemberName;

    @FXML
    private TableColumn<Map<String,String>,String> colReturnDate;

    @FXML
    private TableColumn<Map<String,String>,String> colStatus;

    @FXML
    private TableView<Map<String,String>> tblBorrowingHistory;


    private ObservableList<Map<String, String>> borrowedBookList = FXCollections.observableArrayList();

    @FXML
    void btnBorrowingHistorySearchOnAction(ActionEvent event) {
        borrowedBookList = borrowingHistoryController.searchByMember(SelecterBorrowingHistory_MemeberID.getValue());
        setCurrentlyBorrowedBookTable();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtTotalRecordsCount.setText(borrowingHistoryController.getBorrowingHistoryRecordCount());
        setMember();

        borrowedBookList = borrowingHistoryController.allCurrentlyBorrowedBookData();
        setCurrentlyBorrowedBookTable();
    }
    public void setMember(){
        SelecterBorrowingHistory_MemeberID.setItems(borrowingHistoryController.memberList());
    }
    private void setCurrentlyBorrowedBookTable() {
        colBorrowingID.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getOrDefault("borrowing_id","")));
        colMemberName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("member_name","")));
        colBookTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("title","")));
        colIssueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("issue_date","")));
        colDueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("due_date","")));
        colReturnDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("return_date","")));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("status","")));

        tblBorrowingHistory.setItems(borrowedBookList);
    }
}
