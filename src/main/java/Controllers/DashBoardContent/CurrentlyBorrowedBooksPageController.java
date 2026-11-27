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
import model.BorrowingHistory;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class CurrentlyBorrowedBooksPageController implements Initializable {

    CurentlyBorrowedBooksController curentlyBorrowedBooksController = new CurentlyBorrowedBooksController();
    DashboardController dashboardController = new DashboardController();

    @FXML
    private Button btnCurrentlyBorrowedBooksSearch;

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
    private TableColumn<BorrowingHistory, String> colDueDate;

    @FXML
    private TableColumn<BorrowingHistory, String> colIssueDate;

    @FXML
    private TableColumn<BorrowingHistory,String> colIssueId;

    @FXML
    private TableColumn<BorrowingHistory, String> colMemberName;

    @FXML
    private TableColumn<BorrowingHistory,String> colStatus;

    @FXML
    private TableView<BorrowingHistory> tblCurrentlyBorrowedBook;

    @FXML
    private TextField txtCurrentlyBorrowedBooksCount;

    @FXML
    private TextField txtCurrentlyBorrowedBooksSearchBar;

    private ObservableList<BorrowingHistory> currentlyBorrowedBookList = FXCollections.observableArrayList();


    @FXML
    void btnCurrentlyBorrowedBooksSearchOnAction(ActionEvent event) {
        currentlyBorrowedBookList=
                curentlyBorrowedBooksController.searchBookData(txtCurrentlyBorrowedBooksSearchBar.getText());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtCurrentlyBorrowedBooksCount.setText(dashboardController.borrowingBookCount());

        currentlyBorrowedBookList = curentlyBorrowedBooksController.allCurrentlyBorrowedBookData();
        setCurrentlyBorrowedBookTable();

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
}

