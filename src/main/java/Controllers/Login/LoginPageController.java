package Controllers.Login;

import Controllers.Dashboard.DashboardPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class LoginPageController {

    LoginController loginController = new LoginController();

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnReset;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;


    @FXML
    private Label txtInvalidUser;

    @FXML
    void btnLoginOnAction(ActionEvent event) {


        if (txtUserName.getText().isEmpty() ){
            txtInvalidUser.setText("Please Enter userName");
        }else if (txtPassword.getText().isEmpty()){
            txtInvalidUser.setText("Please Enter Password");
        }else {
            User user = new User();
            user.setName(txtUserName.getText());
            user.setPassword(txtPassword.getText());

            if(loginController.checkUserNameAndPassword(user)) {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard_page.fxml"));

                    stage.setScene(new Scene(loader.load()));

                    DashboardPageController dashboardPageController = loader.getController();

                    dashboardPageController.setUserName(user.getName());


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            }else {
            txtInvalidUser.setText("Invalid Username or Password");
        }

        }
//



    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtUserName.clear();
        txtPassword.clear();
        txtInvalidUser.setText(null);
    }

}
