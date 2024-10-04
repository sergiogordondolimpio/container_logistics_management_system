package View;

import Controller.LoginController;

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
    private LoginController controller; // Controller variable

    public LoginView() {
        // Add action listeners for buttons
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Close the application
            }
        });
    }

    public void setController(LoginController controller) {
        this.controller = controller;  // Set the controller from outside
    }

    private void handleLogin() {
        String username = txtUser.getText();
        String password = txtPass.getText();
        controller.handleLogin(username, password);  // Delegate to controller
    }

    // Methods to display messages or interact with UI
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void closeLogin() {
        // Close the login window
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(loginPanel);
        if (frame != null) {
            frame.dispose();
        }
    }

    public void openMenu() {
        // Logic to open the menu window
        System.out.println("Menu opened.");
    }
}
