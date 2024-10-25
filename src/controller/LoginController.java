package controller;

import dao.UserDAO;
import manager.SessionManager;
import view.LoginView;


import model.User;

public class LoginController {
    private UserDAO userDAO;
    private LoginView login;

    public LoginController(UserDAO userDAO, LoginView login) {
        this.userDAO = userDAO;
        this.login = login;

        login.setController(this);
    }

    // user: su pass: 1234
    public void handleLogin(String username, String password) {
        User user = userDAO.authenticateUser(username, password);
        if (user != null) {
            SessionManager.getInstance().setLoggedInUser(user);
            login.showMessage("Login successful!");
            login.closeLogin();
            login.openMenu();
        } else {
            login.showMessage("Invalid username or password.");
        }
    }

}

