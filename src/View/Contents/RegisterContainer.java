package View.Contents;

import javax.swing.*;
import java.awt.*;

public class RegisterContainer extends JPanel {

    private JTextField codeField;
    private JTextField locationField;
    private JTextField statusField;
    private JTextField arrivalDateField;
    private JButton submitButton;
    private JButton clearButton;

    public RegisterContainer() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;


        // Title Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(50, 0, 50, 0);
        JLabel titleLabel = new JLabel("Registrar Contenedor");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(titleLabel, gbc);

        // Text fields
        codeField = new JTextField(25);
        locationField = new JTextField(25);
        statusField = new JTextField(25);
        arrivalDateField = new JTextField(25);

        gbc.insets = new Insets(5, 10, 5, 10);

        addFormField("Código:", codeField, gbc, 1);
        addFormField("Ubicación:", locationField, gbc, 2);
        addFormField("Estado:", statusField, gbc, 3);
        addFormField("Día de Llegada:", arrivalDateField, gbc, 4);

        // Add the buttons
        gbc.insets = new Insets(30, 0, 30, 0);
        addButtons(gbc, 6);
    }

    // Helper method to add form fields
    private void addFormField(String labelText, JTextField textField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setPreferredSize(new Dimension(250, 40));
        add(textField, gbc);
    }

    // Helper method to add buttons
    private void addButtons(GridBagConstraints gbc, int row) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());

        // Button dimensions
        Dimension buttonSize = new Dimension(150, 40);

        // Submit button
        submitButton = new JButton("Ingresar");
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitButton.setPreferredSize(buttonSize);
        submitButton.setMinimumSize(buttonSize);
        submitButton.setMaximumSize(buttonSize);
        buttonPanel.add(submitButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        // Clear button
        clearButton = new JButton("Limpiar");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setPreferredSize(buttonSize);
        clearButton.setMinimumSize(buttonSize);
        clearButton.setMaximumSize(buttonSize);
        buttonPanel.add(clearButton);

        buttonPanel.add(Box.createHorizontalGlue());

        // Add the button panel to the grid bag layout
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Add action listeners for the buttons
        submitButton.addActionListener(e -> {
            String code = codeField.getText();
            String location = locationField.getText();
            String status = statusField.getText();
            String arrivalDate = arrivalDateField.getText();

            JOptionPane.showMessageDialog(RegisterContainer.this,
                    "Container Ingresado:\nCódigo: " + code +
                            "\nUbicación: " + location +
                            "\nEstado: " + status +
                            "\nDía de Llegada: " + arrivalDate);
        });

        clearButton.addActionListener(e -> {
            codeField.setText("");
            locationField.setText("");
            statusField.setText("");
            arrivalDateField.setText("");
        });
    }
}
