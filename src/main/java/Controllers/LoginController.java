package Controllers;

public class LoginController {
    public boolean checkUserNameAndPassword(String userName, String password) {
        if (userName.equals("Handler") && password.equals("12345")){
            return true;
        }
        return false;
    }
}
