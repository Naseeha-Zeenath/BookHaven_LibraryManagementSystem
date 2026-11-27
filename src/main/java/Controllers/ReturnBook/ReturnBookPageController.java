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
import model.Borrow;
import model.BorrowingHistory;
import model.ReturnBookDetails;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ReturnBookPageController implements Initializable{

    CurentlyBorrowedBooksController curentlyBorrowedBooksController = new CurentlyBorrowedBooksController();
    ReturnBookController returnBookController = new ReturnBookController();

    @FXML
    private DatePicker selectReturnBookDate;

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
    private TableColumn<BorrowingHistory,String > colBookTitle;

    @FXML
    private TableColumn<BorrowingHistory,String> colDueDate;

    @FXML
    private TableColumn<BorrowingHistory,String> colIssueDate;

    @FXML
    private TableColumn<BorrowingHistory,String> colIssueId;

    @FXML
    private TableColumn<BorrowingHistory,String > colMemberName;

    @FXML
    private TableColumn<BorrowingHistory,String> colStatus;

    @FXML
    private TableView<BorrowingHistory> tblCurrentlyBorrowedBook;

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
    private Label txtReturnBookOverdueDaysCount;

    @FXML
    private Label txtInvalidReturnBook;

    @FXML
    private TextField txtBorrowingId;

    @FXML
    private TextField txtReturnBookSearchBar;

    private ObservableList<BorrowingHistory> currentlyBorrowedBookList = FXCollections.observableArrayList();


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
    void btnReturnBookClearOnAction(ActionEvent event) {
//TODO
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

        if (txtBorrowingId.getText().trim().isEmpty()) {
            txtInvalidReturnBook.setText("Please select the borrowing details.");

        } else if (selectReturnBookDate.getValue() == null) {
            txtInvalidReturnBook.setText("Please select the return date.");

        }else {
            Borrow borrow = new Borrow();

            borrow.setId(Integer.valueOf(txtBorrowingId.getText()));
            borrow.setReturnDate(selectReturnBookDate.getValue());

            txtInvalidReturnBook.setText(returnBookController.bookReturn(borrow));
        }
    }

    @FXML
    void btnReturnBookSearchOnAction(ActionEvent event) {
        currentlyBorrowedBookList= curentlyBorrowedBooksController.searchBookData(txtReturnBookSearchBar.getText());
        setCurrentlyBorrowedBookTable();

        tblCurrentlyBorrowedBook.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, Value) -> {
            if (Value != null) {
                txtMemberName.setText(Value.getMemberName());
                txtBorrowingId.setText(Value.getBorrowingId());

                String title = Value.getBookTitle();
                if (title != null && title.length() > 30) {
                    title = title.substring(0, 30) + "\n" +
                            title.substring(30);
                }
                txtBookTitle.setText(title);

                txtBorrowedDate.setText(Value.getIssueDate());
                txtDueDate.setText(Value.getDueDate());

                ReturnBookDetails details = returnBookController.selectDetails(Value.getBorrowingId());

                if (details != null) {
                    txtMemberId.setText(details.getMemberId());
                    txtEmail.setText(details.getEmail());
                    txtPhoneNo.setText(details.getPhone());
                    txtISBN.setText(details.getIsbn());
                }

            }
        });

    }



    private void setCurrentlyBorrowedBookTable() {
        colIssueId.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getBorrowingId()));
        colMemberName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getMemberName()));
        colBookTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getBookTitle()));
        colIssueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getIssueDate()));
        colDueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getDueDate()));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getStatus()));

        tblCurrentlyBorrowedBook.setItems(currentlyBorrowedBookList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectReturnBookDate.valueProperty().addListener((observable, oldValue, newValue) ->
            calculateOverdueDays());
    }

    private void calculateOverdueDays() {

        if (selectReturnBookDate.getValue() != null && !txtDueDate.getText().isEmpty()) {

            LocalDate dueDate = LocalDate.parse(txtDueDate.getText());
            LocalDate returnDate = selectReturnBookDate.getValue();

            long overdueDays = 0;

            if (returnDate.isAfter(dueDate)) {
                overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);
            }

            txtReturnBookOverdueDaysCount.setText(String.valueOf(overdueDays));
        }
    }
}
