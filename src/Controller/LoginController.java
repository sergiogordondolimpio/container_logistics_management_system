package Controller;

import DAO.UserDAO;
import View.LoginView;


public class LoginController {
    private UserDAO userDAO;
    private LoginView login;

    public LoginController(UserDAO userDAO, LoginView login) {
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

