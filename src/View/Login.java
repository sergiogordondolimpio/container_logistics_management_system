package View;

import Controller.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField userTextField;
    private JPasswordField passTextField;
    private JButton loginButton;
    private JButton registerButton;
    private LoginController controller;

    public Login() {
        // Frame
        setTitle("Login");
        setSize(600, 500); // Frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title label
        JLabel titleLabel = new JLabel("Sistema de Gestión de Contenedores - Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel input
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("Username:"), gbc);

        // Username Input
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        userTextField = new JTextField(15);
        userTextField.setPreferredSize(new Dimension(200, 40));
        inputPanel.add(userTextField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST; // Align label to the right within its cell
        inputPanel.add(new JLabel("Password:"), gbc);

        // Password Input
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align input field to the left within its cell
        passTextField = new JPasswordField(15);
        passTextField.setPreferredSize(new Dimension(200, 40)); // Set preferred size for larger input fields (width, height)
        inputPanel.add(passTextField, gbc);

        // Center input
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Panel Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // GridLayout ensures both buttons have equal size

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(loginButton);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        add(mainPanel);

        setVisible(true);
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    private void handleLogin() {
        String username = userTextField.getText();
        String password = new String(passTextField.getPassword());
        controller.handleLogin(username, password);
    }

    private void handleRegister() {
        String username = userTextField.getText();
        String password = new String(passTextField.getPassword());
        controller.handleRegister(username, password);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void closeLogin() {
        this.dispose();
    }

    public void openMenu() {
        new Menu().setVisible(true);
    }

}
