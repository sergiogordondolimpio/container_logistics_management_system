package View.Contents;

import Controller.ContainerController;
import Controller.ModuleController;
import Model.Container;
import Model.Module;
import Model.Status;
import Validation.ModuleValidator;
import Validation.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DevanningView {

    private JButton btnAdd;
    private JButton btnClear;
    private JButton btnDelete;
    private JComboBox<String> cbContainer;
    private JPanel devanningPanel;
    private JTable tbModules;
    private JTextField txtCode;
    private JTextField txtDescription;
    private JTextField txtWeight;
    private JComboBox cbStatus;
    private Module module;
    private ContainerController containerController;
    private ModuleController moduleController;
    private ModuleValidator validator;


    public DevanningView() {
        containerController = new ContainerController();
        moduleController = new ModuleController();
        validator = new ModuleValidator(moduleController);
        populateContainerComboBox();
        populateStatusComboBox();
        setTable();

        cbContainer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTable();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModule();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteModule();
            }
        });
    }

    private void deleteModule() {
        int selectedRow = tbModules.getSelectedRow();

        if (selectedRow != -1) {
            String code = tbModules.getValueAt(selectedRow, 0).toString();
            if ( moduleController.deleteByCode(code)) {
                JOptionPane.showMessageDialog(null, "¡El módulo fue eliminado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                setTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error: El módulo no pudo ser eliminado!", "Error", JOptionPane.ERROR_MESSAGE);
            }


        } else {
            // No row is selected
            JOptionPane.showMessageDialog(null, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Set table of modules
    private void setTable() {
        Vector<String> columnNames = new Vector<>(Arrays.asList("Código", "Descripción", "Estatus", "Peso"));

        Vector<Vector<Object>> data = new Vector<>();

        if (cbContainer.getSelectedItem() != null) {
            Integer idContainer = containerController.getIdContainerByCode(cbContainer.getSelectedItem().toString());
            List<Module> modules =  moduleController.getModulesByContainer(idContainer);

            for (Module module : modules) {
                data.add(new Vector<>(Arrays.asList(
                        module.getCode(), module.getDescription(), module.getStatus(), module.getWeight()
                )));
            }
        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tbModules.setModel(model);

        JTableHeader header = tbModules.getTableHeader();
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 18));

    }

    // Populate the combo box with the containers get from database
    private void populateContainerComboBox() {

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<Container> containers = containerController.getAllContainers();
        for (Container container : containers) {
            model.addElement(container.getCode());
        }
        cbContainer.setModel(model);

        cbContainer.setRenderer(new DefaultListCellRenderer() {
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

    // Populate the JComboBox
    private void populateStatusComboBox() {
        DefaultComboBoxModel<Status> model = new DefaultComboBoxModel<>();
        for (Status status : Status.values()) {
            model.addElement(status);
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

    private void setModule() {
        List<Validator> validations = Arrays.asList(
                () -> validator.validateRequiredFields(txtCode.getText(), txtDescription.getText(), txtWeight.getText()),
                () -> validator.validateCode(txtCode.getText()),
                () -> validator.isCodeUnique(txtCode.getText()),
                () -> validator.validateNumberWithDecimal(txtWeight.getText())
        );

        for (Validator validation : validations) {
            String errorMessage = validation.validate();
            if (errorMessage != null) {
                showErrorDialog(errorMessage);
                return;
            }
        }

        module = new Module();
        module.setDescription(txtDescription.getText());
        module.setStatus(cbStatus.getSelectedItem().toString());
        module.setCode(txtCode.getText());
        module.setWeight(Float.parseFloat(txtWeight.getText()));
        module.setIdContainer(containerController.getIdContainerByCode(cbContainer.getSelectedItem().toString()));

        if (moduleController.save(module) == null) {
            JOptionPane.showMessageDialog(null, "Error: El módulo no pudo ser agregado!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "¡El módulo fue agregado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setTable();
        }

    }

    // Show error dialogs
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Validación errores", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        txtCode.setText("");
        txtDescription.setText("");
        cbStatus.setSelectedIndex(0);
        txtWeight.setText("");
    }

    public JPanel getPanel() {
        return devanningPanel;
    }
}
