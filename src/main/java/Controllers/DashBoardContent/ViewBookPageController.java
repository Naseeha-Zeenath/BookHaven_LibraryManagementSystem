package Controllers.DashBoardContent;

import Controllers.Dashboard.DashboardController;
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

public class ViewBookPageController implements Initializable {
    ViewBookController viewBookController = new ViewBookController();
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
    private Button btnTotalBooks_Search;

    @FXML
    private TableColumn<Map<String,String>,String> colAuthor;

    @FXML
    private TableColumn<Map<String,String>,String> colBookId;

    @FXML
    private TableColumn<Map<String,String>,String> colCategory;

    @FXML
    private TableColumn<Map<String,String>,String> colIsbn;

    @FXML
    private TableColumn<Map<String,String>,String>  colOnStock;

    @FXML
    private TableColumn<Map<String,String>,String> colPublished_year;

    @FXML
    private TableColumn<Map<String,String>,String> colQuantity;

    @FXML
    private TableColumn<Map<String,String>,String>  colTitle;

    @FXML
    private TableView<Map<String, String>> tblBooks;


    @FXML
    private Label txtTotalBookCount;

    @FXML
    private Label txtTotalBooks_AvailableBookCount;

    @FXML
    private Label txtTotalBooks_IssuedBookCount;

    @FXML
    private Label txtTotalBooks_OverdueBookCount;

    @FXML
    private TextField txtTotalBooks_SearchBar;

    private ObservableList<Map<String, String>> masterBookList = FXCollections.observableArrayList();
    private FilteredList<Map<String, String>> filteredStudentList;

    private void setMemberTable(){
        colBookId.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getOrDefault("book_id","")));
        colIsbn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("isbn","")));
        colTitle.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("title","")));
        colAuthor.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("author_name","")));
        colCategory.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("category_name","")));
        colPublished_year.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("published_year","")));
        colQuantity.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("quantity","")));
        colOnStock.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getOrDefault("available_quantity","")));

        tblBooks.setItems(masterBookList);

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
        void btnTotalBooks_SearchOnAction(ActionEvent event) {
           masterBookList = viewBookController.searchBookdata(txtTotalBooks_SearchBar.getText());
           setMemberTable();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        masterBookList = viewBookController.allBookData();
        setMemberTable();

        txtTotalBookCount.setText(dashboardController.bookCount());
        txtTotalBooks_AvailableBookCount.setText(dashboardController.availableBookCount());

    }
}
