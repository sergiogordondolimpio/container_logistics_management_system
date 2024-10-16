package View.Contents;

import Controller.ContainerController;
import Model.Container;
import Model.Status;
import Utils.DateTimeUtil;
import Validation.ContainerValidator;
import Validation.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class RegisterContainerView {

    private ContainerValidator validator;
    private JPanel registerContainerPanel;
    private JTextField txtDate;
    private JTextField txtHour;
    private JTextField txtLocation;
    private JTextField textCode;
    private JButton btnClear;
    private JButton btnAdd;
    private JComboBox<Status> cbStatus;
    private Container container;
    private ContainerController containerController;

    public RegisterContainerView() {

        containerController = new ContainerController();
        validator = new ContainerValidator(containerController);
        populateStatusComboBox();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContainer();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    // Populate the JComboBox
    private void populateStatusComboBox() {
        DefaultComboBoxModel<Status> model = new DefaultComboBoxModel<>();
        for (Status status : Status.values()) {
            model.addElement(status); // Add each enum value
        }
        cbStatus.setModel(model);

        cbStatus.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Status) {
                    setText(((Status) value).getLabel());
                }
                return renderer;
            }
        });
    }

    private void setContainer() {
        List<Validator> validations = Arrays.asList(
                () -> validator.validateRequiredFields(txtDate.getText(), txtHour.getText(), txtLocation.getText(), textCode.getText()),
                () -> validator.validateDate(txtDate.getText()),
                () -> validator.validateHour(txtHour.getText()),
                () -> validator.validateCode(textCode.getText()),
                () -> validator.isCodeUnique(textCode.getText())
        );

        for (Validator validation : validations) {
            String errorMessage = validation.validate();
            if (errorMessage != null) {
                showErrorDialog(errorMessage);
                return;
            }
        }

        container = new Container();
        container.setLocation(txtLocation.getText());
        container.setStatus(cbStatus.getSelectedItem().toString());
        container.setCode(textCode.getText());
        container.setArriveDate(DateTimeUtil.createDateTime(txtDate.getText(), txtHour.getText()));

        if (containerController.saveContainer(container) == null) {
            JOptionPane.showMessageDialog(null, "Error: El contenedor no pudo ser agregado!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "¡El contenedor fue agregado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // Show error dialogs
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Validación errores", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        txtDate.setText("");
        txtHour.setText("");
        txtLocation.setText("");
        cbStatus.setSelectedIndex(0);
        textCode.setText("");
    }

    public JPanel getPanel() {
        return registerContainerPanel;
    }

}


