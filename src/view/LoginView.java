package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    public JPanel loginPanel;
    private JTextField txtUser;
    private JTextField txtPass;
    private JButton btnLogin;
    private JButton btnCancel;
    private JLabel lblUser;
    private JLabel lblPassword;
    private LoginController controller;

    public LoginView() {
        // Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Close the application
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Set the controller from outside
    public void setController(LoginController controller) {
        this.controller = controller;
    }

    // Login
    private void handleLogin() {
        String username = txtUser.getText();
        String password = txtPass.getText();
        controller.handleLogin(username, password);
    }

    // Methods to display messages or interact with UI
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    // Close panel
    public void closeLogin() {
        // Close the login window
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(loginPanel);
        if (frame != null) {
            frame.dispose();
        }
    }

    // Open menu
    public void openMenu() {
        JFrame frame = new JFrame("Sistema de Gestión de Contenedores y Logística");
        frame.setContentPane(new MenuView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
