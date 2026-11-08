package Controllers.DashBoardContent;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class OverdueBooksPageController implements Initializable {

    OverdueBooksController overdueBooksController = new OverdueBooksController();
    DashboardController dashboardController = new DashboardController();

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
    private Button btnOverdueBooks_Search;

    @FXML
    private TableColumn<Map<String,String>,String> colBookTitle;

    @FXML
    private TableColumn<Map<String,String>,String> colDaysOverdue;

    @FXML
    private TableColumn<Map<String,String>,String> colDueDate;

    @FXML
    private TableColumn<Map<String,String>,String> colIssueDate;

    @FXML
    private TableColumn<Map<String,String>,String> colIssueID;

    @FXML
    private TableColumn<Map<String,String>,String> colMemberName;

    @FXML
    private TableColumn<Map<String,String>,String> colStatus;

    @FXML
    private TableView<Map<String,String>> tblOverdueBooks;

    @FXML
    private TextField txtOverdueBooksCount;


    private ObservableList<Map<String, String>> OverdueBookList = FXCollections.observableArrayList();

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
    void btnOverdueBooks_PageNextOnAction(ActionEvent event) {

    }

    @FXML
    void btnOverdueBooks_PagePrevOnAction(ActionEvent event) {

    }

    @FXML
    void btnOverdueBooks_SearchOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtOverdueBooksCount.setText(dashboardController.overdueBookCount());

        OverdueBookList = overdueBooksController.loadOverdueBookData();
        currentlyBorrowedBookTable();

    }

    private void currentlyBorrowedBookTable() {
        colIssueID.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getOrDefault("borrowing_id","")));
        colMemberName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("member_name","")));
        colBookTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("title","")));
        colIssueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("issue_date","")));
        colDueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("due_date","")));
        colDaysOverdue.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("days_overdue","")));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("status","")));

        tblOverdueBooks.setItems(OverdueBookList);
    }
}
