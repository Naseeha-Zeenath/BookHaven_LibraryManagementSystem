package Controllers.AddBook;

import Controllers.AddBook.Author.AddAuthorController;
import Controllers.AddBook.Category.AddCategoryController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookPageController implements Initializable {

    AddBookController addBookController =  new AddBookController();
    AddAuthorController addAuthorController = new AddAuthorController();
    AddCategoryController addCategoryController = new AddCategoryController();

    @FXML
    private ComboBox<String> selectorAddNewBookAuthor;

    @FXML
    private ComboBox<String> selectorAddNewBookCategory;

    @FXML
    private Button btnAddNewAuthor;

    @FXML
    private Button btnAddNewCategory;

    @FXML
    private Button btnAddNewBook;

    @FXML
    private Button btnAddNewBookClear;

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
    private TextField txtAddNewBookBookTitle;

    @FXML
    private TextField txtAddNewBookISBN;

    @FXML
    private TextField txtAddNewBookPublishedYear;

    @FXML
    private TextField txtAddNewBookQuantity;

    @FXML
    private Label txtInvalidAddBook;





    Stage stage2 = new Stage();
    @FXML
    void btnAddNewAuthorOnAction(ActionEvent event) {

        try {
            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddAuthor_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage2.show();

    }

    @FXML
    void btnAddNewCategoryOnAction(ActionEvent event) {

        try {
            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddCategory_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage2.show();
    }

    @FXML
    void btnAddNewBookClearOnAction(ActionEvent event) {
        txtAddNewBookISBN.clear();
        txtAddNewBookBookTitle.clear();
        txtAddNewBookPublishedYear.clear();
        txtAddNewBookQuantity.clear();
        txtInvalidAddBook.setText("");
        selectorAddNewBookAuthor.setItems(null);
        selectorAddNewBookCategory.setItems(null);
        setAuthor();
        setCategory();
    }


    @FXML
    void btnAddNewBookOnAction(ActionEvent event) {
        Book book = new Book();

        if (txtAddNewBookISBN.getText().isBlank() ||
                txtAddNewBookBookTitle.getText().isEmpty() ||
                selectorAddNewBookAuthor.getValue() ==null ||
                selectorAddNewBookCategory.getValue() == null ||
                txtAddNewBookPublishedYear.getText().isEmpty() ||
                txtAddNewBookQuantity.getText().isEmpty()) {
            txtInvalidAddBook.setText("Please fill in all required fields...");
        }else if (txtAddNewBookISBN.getLength() != 10 && txtAddNewBookISBN.getText().length() != 13)  {
            txtInvalidAddBook.setText( "ISBN must contain 10 or 13 characters.");
        } else if (txtAddNewBookBookTitle.getLength() > 100) {
            txtInvalidAddBook.setText( "Book title cannot exceed 100 characters...");
        }else {

            int publishedYear = 0;
            int quantity = 0;
            try {
                publishedYear = Integer.parseInt((txtAddNewBookPublishedYear.getText()));
                quantity = Integer.parseInt(txtAddNewBookQuantity.getText());
            } catch (NumberFormatException e) {
                txtInvalidAddBook.setText("Published year and quantity must be valid integers...");
            }


        if (publishedYear < 0 || publishedYear > java.time.Year.now().getValue()) {
            txtInvalidAddBook.setText( "Please enter a valid published year...");
        } else if (txtAddNewBookPublishedYear.getLength() != 4) {
            txtInvalidAddBook.setText( "Published year must contain exactly 4 digits...");
        } else if (quantity < 0) {
            txtInvalidAddBook.setText( "Quantity cannot be negative...");
        } else if (txtAddNewBookQuantity.getLength() > 4) {
            txtInvalidAddBook.setText( "Book quantity is too large...");
        }else {

            book.setIsbn(txtAddNewBookISBN.getText());
            book.setTitle(txtAddNewBookBookTitle.getText());
            book.setYear(publishedYear);
            book.setQuantity(quantity);
            book.setAvailableQty(quantity);
            book.setAuthorId(Integer.valueOf(selectorAddNewBookAuthor.getValue().split(" ")[0]));
            book.setCategoryId(Integer.valueOf(selectorAddNewBookCategory.getValue().split(" ")[0]));

            txtInvalidAddBook.setText(addBookController.InsertBookOnDB(book));

        }}
    }




    @FXML
    void btnLogOutOnAction(ActionEvent event) {
//        TODO
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
        setAuthor();
        setCategory();
    }

    public void setAuthor(){
        selectorAddNewBookAuthor.setItems(addAuthorController.author());
    }

    public void setCategory(){
        selectorAddNewBookCategory.setItems(addCategoryController.category());
    }


}
