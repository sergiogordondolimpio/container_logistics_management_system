package Controller;

import Model.UserDAO;
import View.Login;


public class LoginController {
    private UserDAO userDAO;
    private Login login;

    public LoginController(UserDAO userDAO, Login login) {
        this.userDAO = userDAO;
        this.login = login;

        login.setController(this);
    }

    // user: tu pass: pass
    public void handleLogin(String username, String password) {
        if (userDAO.authenticateUser(username, password)) {
            login.showMessage("Login successful!");
            login.closeLogin();
            login.openMenu();
        } else {
            login.showMessage("Invalid username or password.");
        }
    }

    public void handleRegister(String username, String password) {
        if (userDAO.registerUser(username, password)) {
            login.showMessage("User registered successfully!");
        } else {
            login.showMessage("User registration failed.");
        }
    }
}

