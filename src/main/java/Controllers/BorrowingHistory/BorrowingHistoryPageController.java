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
import model.BorrowingHistory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BorrowingHistoryPageController implements Initializable {

    BorrowingHistoryController borrowingHistoryController = new BorrowingHistoryController();


    @FXML
    private DatePicker selecterBorrowingHistoryDate;

    @FXML
    private ComboBox<String> selecterBorrowingHistoryMemeberID;

    @FXML
    private Label txtTotalRecordsCount;

    @FXML
    private Button btnBorrowingHistorySearch;

    @FXML
    private Button btnBorrowingHistoryReset;

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
    private TableColumn<BorrowingHistory, String> colBookTitle;

    @FXML
    private TableColumn<BorrowingHistory,String> colDueDate;

    @FXML
    private TableColumn<BorrowingHistory,String> colIssueDate;

    @FXML
    private TableColumn<BorrowingHistory,String> colBorrowingID;

    @FXML
    private TableColumn<BorrowingHistory,String> colMemberName;

    @FXML
    private TableColumn<BorrowingHistory,String> colReturnDate;

    @FXML
    private TableColumn<BorrowingHistory,String> colStatus;

    @FXML
    private TableView<BorrowingHistory> tblBorrowingHistory;


    private ObservableList<BorrowingHistory> borrowedBookList = FXCollections.observableArrayList();

    @FXML
    void btnBorrowingHistoryResetOnAction(ActionEvent event) {
        selecterBorrowingHistoryMemeberID.setItems(null);
        setMember();
        selecterBorrowingHistoryDate.setValue(null);

        borrowedBookList= borrowingHistoryController.allCurrentlyBorrowedBookData();
        setCurrentlyBorrowedBookTable();

    }

    @FXML
    void btnBorrowingHistorySearchOnAction(ActionEvent event) {

        String selectMember = selecterBorrowingHistoryMemeberID.getValue();

        LocalDate selectedDate = selecterBorrowingHistoryDate.getValue();


        if ( selectMember!=null && selectedDate != null) {

            borrowedBookList = borrowingHistoryController.searchByMemberAndDate(selectMember, String.valueOf(selectedDate));

        } else if (selectMember !=null && !selectMember.isEmpty()) {

            borrowedBookList = borrowingHistoryController.searchByMember(selectMember);

        } else if (selectedDate != null) {

            borrowedBookList = borrowingHistoryController.searchByDate(String.valueOf(selectedDate));

        } else {

            borrowedBookList = borrowingHistoryController.allCurrentlyBorrowedBookData();
        }

        setCurrentlyBorrowedBookTable();


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
        txtTotalRecordsCount.setText(borrowingHistoryController.getBorrowingHistoryRecordCount());
        setMember();

        borrowedBookList = borrowingHistoryController.allCurrentlyBorrowedBookData();
        setCurrentlyBorrowedBookTable();
    }
    public void setMember(){
        selecterBorrowingHistoryMemeberID.setItems(borrowingHistoryController.memberList());
    }

    private void setCurrentlyBorrowedBookTable() {
        colBorrowingID.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getBorrowingId()));
        colMemberName.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getMemberName()));
        colBookTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getBookTitle()));
        colIssueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getIssueDate()));
        colDueDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getDueDate()));
        colReturnDate.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getReturnDate()));
        colStatus.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getStatus()));

        tblBorrowingHistory.setItems(borrowedBookList);
    }
}
