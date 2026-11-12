package Controllers.ReturnBook;

import Controllers.DashBoardContent.CurentlyBorrowedBooksController;
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

public class ReturnBookPageController implements Initializable {

    CurentlyBorrowedBooksController curentlyBorrowedBooksController = new CurentlyBorrowedBooksController();

    @FXML
    private DatePicker SelectReturnBookDate;

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
    private Button btnReturnBook;

    @FXML
    private Button btnReturnBookClear;

    @FXML
    private Button btnReturnBookSearch;

    @FXML
    private TableColumn<Map<String,String>,String > colBookTitle;

    @FXML
    private TableColumn<Map<String,String>,String> colDueDate;

    @FXML
    private TableColumn<Map<String,String>,String> colIssueDate;

    @FXML
    private TableColumn<Map<String,String>,String> colIssueId;

    @FXML
    private TableColumn<Map<String,String>,String > colMemberName;

    @FXML
    private TableColumn<Map<String ,String>,String> colStatus;

    @FXML
    private TableView<Map<String,String>> tblCurrentlyBorrowedBook;

    @FXML
    private Label txtBookTitle;

    @FXML
    private Label txtBorrowedDate;

    @FXML
    private Label txtDueDate;

    @FXML
    private Label txtEmail;

    @FXML
    private Label txtISBN;

    @FXML
    private Label txtMemberId;

    @FXML
    private Label txtMemberName;

    @FXML
    private Label txtPhoneNo;

    @FXML
    private Label txtReturnBook_OverdueDaysCount;

    @FXML
    private TextField txtReturnBook_SearchBar;

    private ObservableList<Map<String, String>> currentlyBorrowedBookList = FXCollections.observableArrayList();

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

    }

    @FXML
    void btnReturnBookClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookSearchOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentlyBorrowedBookList = curentlyBorrowedBooksController.allCurrentlyBorrowedBookData();
        setCurrentlyBorrowedBookTable();
    }
    private void setCurrentlyBorrowedBookTable() {
        colIssueId.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getOrDefault("borrowing_id","")));
        colMemberName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("member_name","")));
        colBookTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("title","")));
        colIssueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("issue_date","")));
        colDueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("due_date","")));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("status","")));

        tblCurrentlyBorrowedBook.setItems(currentlyBorrowedBookList);
    }
}
