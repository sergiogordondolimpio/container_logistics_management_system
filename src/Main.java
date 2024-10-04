import View.MenuView;

import javax.swing.*;


public class Main {

    private JPanel panel;

    public static void main(String[] args) {
//        UserDAO userDAO = UserDAO.getInstance(); // Singleton pattern
//        LoginView loginView = new LoginView();
//        LoginController controller = new LoginController(userDAO, loginView);
//        loginView.setController(controller);

//        JFrame frame = new JFrame("App");
//        frame.setContentPane(loginView.loginPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

        JFrame frame = new JFrame("Sistema de Gestión de Contenedores y Logística");
        frame.setContentPane(new MenuView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }
}