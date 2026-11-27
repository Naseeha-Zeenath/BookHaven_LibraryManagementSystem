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
import model.Borrow;
import model.BorrowingHistory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class IssueBookPageController implements Initializable {

    IssueBookController issueBookController = new IssueBookController();

    @FXML
    private ComboBox<String> selectBook;

    @FXML
    private DatePicker selectDueDate;

    @FXML
    private DatePicker selectIssueDate;

    @FXML
    private ComboBox<String> selectMemeber;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnIssueBookClear;

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
    private TableColumn<BorrowingHistory, String> colBookTitle;

    @FXML
    private TableColumn<BorrowingHistory, String> colDueDate;

    @FXML
    private TableColumn<BorrowingHistory, String> colIssueDate;

    @FXML
    private TableColumn<BorrowingHistory, String> colIssueId;

    @FXML
    private TableColumn<BorrowingHistory, String> colMemberName;

    @FXML
    private TableColumn<BorrowingHistory, String> colStatus;

    @FXML
    private TableView<BorrowingHistory> tblRecentIssues;

    private ObservableList<BorrowingHistory> recentIssuesList = FXCollections.observableArrayList();


    @FXML
    void btnIssueBookClearOnAction(ActionEvent event) {
        selectBook.setItems(null);
        selectMemeber.setItems(null);
        selectDueDate.setValue(null);
        selectIssueDate.setValue(null);
        txtInvalidAddIssueBook.setText(null);

        selectBook.setItems(issueBookController.bookList());
        selectMemeber.setItems(issueBookController.memberList());
    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {

        Borrow borrow = new Borrow();

        if(selectMemeber.getValue() == null){
            txtInvalidAddIssueBook.setText( "Please select a member");
        } else if (selectBook.getValue() == null) {
            txtInvalidAddIssueBook.setText( "Please select a book.");
        } else if (selectIssueDate.getValue() == null) {
            txtInvalidAddIssueBook.setText( "Please select an issue date.");
        }else if (selectIssueDate.getValue().isAfter(LocalDate.now())) {
            txtInvalidAddIssueBook.setText(  "Issue date cannot be in the future.");
        } else if (selectDueDate.getValue() == null) {
            txtInvalidAddIssueBook.setText( "Please select a due date.");
        } else if (selectDueDate.getValue().isBefore(selectIssueDate.getValue())) {
            txtInvalidAddIssueBook.setText( "Due date must be after the issue date.");
        }else {

            borrow.setMemberId(selectMemeber.getValue().split(" ")[0]);

            borrow.setBookId(Integer.parseInt(selectBook.getValue().split(" ")[0]));

            borrow.setIssueDate(selectIssueDate.getValue());

            borrow.setDueDate(selectDueDate.getValue());

            String result = issueBookController.bookIssued(borrow);

            txtInvalidAddIssueBook.setText(result);

            if (result.equals("Success!")) {
                recentIssuesList.setAll(
                        issueBookController.loadRecentIssuesData());
            }
        }

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
//TODO
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

        selectBook.setItems(issueBookController.bookList());
        selectMemeber.setItems(issueBookController.memberList());

        recentIssuesList = issueBookController.loadRecentIssuesData();
        setRecentIssuesTable();
    }

    private void setRecentIssuesTable() {
        colIssueId.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getBorrowingId()));
        colMemberName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getMemberName()));
        colBookTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getBookTitle()));
        colIssueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getIssueDate()));
        colDueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getDueDate()));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getStatus()));

        tblRecentIssues.setItems(recentIssuesList);
    }
}
