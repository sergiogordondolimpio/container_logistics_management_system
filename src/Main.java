import controller.LoginController;
import dao.UserDAO;
import view.LoginView;
import view.MenuView;

import javax.swing.*;


public class Main {

    private JPanel panel;

    public static void main(String[] args) {
        UserDAO userDAO = UserDAO.getInstance();
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(userDAO, loginView);
        loginView.setController(controller);

        JFrame frame = new JFrame("App");
        frame.setContentPane(loginView.loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}