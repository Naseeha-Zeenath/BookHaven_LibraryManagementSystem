package Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardPageController {


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
    private Button currentlyBorrowedBooksViewPanel;

    @FXML
    private Button overdueBooksViewPanel;

    @FXML
    private Button totalBooksViewPanel;

    @FXML
    private Button totalMembersViewPanel;

    @FXML
    private Label txtActiveMembersCount;

    @FXML
    private Label txtAvailableBookCount;

    @FXML
    private Label txtCurrentlyBorrowedBooksCount;

    @FXML
    private Label txtIssuedCount;

    @FXML
    private Label txtOverdueBooksCount;

    @FXML
    private Label txtTotalBooksCount;

    @FXML
    private Label txtTotalMembersCount;

    @FXML
    private Label txtWelcomeUsername;

    GetMemberCount getMemberCount = new GetMemberCount();
    GetBookCount getBookCount = new GetBookCount();
    @FXML
    public void initialize() {
        txtTotalMembersCount.setText(getMemberCount.memberCount());
        txtTotalBooksCount.setText(getBookCount.bookCount());
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
    void btnOverdueBooksOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DahboardContentView/OverdueBooks_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnTotalBooksOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DahboardContentView/TotalBooks_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnTotalMembersOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DahboardContentView/TotalMembers_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnCurrentlyBorrowedBooksOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DahboardContentView/CurrentlyBorrowedBooks_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

}
