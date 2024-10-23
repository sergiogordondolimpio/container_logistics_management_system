package View.Contents;

import Controller.ContainerController;
import Controller.ModuleController;
import Controller.MovementHistoryController;
import Manager.SessionManager;
import Model.*;
import Model.Container;
import Utils.DateTimeUtil;
import Validation.DateValidator;
import Validation.Validator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class ReportView {

    private JPanel reportPanel;
    private JTextField txtFromDate;
    private JComboBox cbContainer;
    private JTable tblContainer;
    private JTextField txtToDate;
    private JButton btnSearch;
    private JTable tblModule;
    private ContainerController containerController;
    private ModuleController moduleController;
    private MovementHistoryController movementHistoryController;
    private DateValidator validator;

    public ReportView() {
        containerController = new ContainerController();
        moduleController = new ModuleController();
        movementHistoryController = new MovementHistoryController();
        validator = new DateValidator();

        populateContainerComboBox();
        setContainerTable();
        setModuleTable();

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTables();
            }
        });
    }

    private void setTables() {
        if (!Objects.equals(txtFromDate.getText(), "") && !Objects.equals(txtToDate.getText(), "")) {
            List<Validator> validations = Arrays.asList(
                    () -> validator.validateDateFormat(txtToDate.getText()),
                    () -> validator.validateDateFormat(txtFromDate.getText()),
                    () -> validator.validateDateRange(txtFromDate.getText(), txtToDate.getText())
            );

            for (Validator validation : validations) {
                String errorMessage = validation.validate();
                if (errorMessage != null) {
                    showErrorDialog(errorMessage);
                    return;
                }
            }
        }

        setContainerTable();
        setModuleTable();
    }

    private void setModuleTable() {
        Vector<String> columnNames = new Vector<>(Arrays.asList("Historial", "Tipo", "Codigo", "Estado", "descripcion", "peso", "Operador"));
        Vector<Vector<Object>> data = new Vector<>();

        if (cbContainer.getSelectedItem() != null) {
            Integer idContainer = containerController.getIdContainerByCode(cbContainer.getSelectedItem().toString());
            List<Module> modules = moduleController.getModulesByContainer(idContainer);

            for (Module module : modules) {
                List<MovementHistory> movements =  movementHistoryController.getHistoryByIdItem(module.getId(), ItemType.MODULE.getLabel());

                for (MovementHistory movement : movements) {
                    data.add(new Vector<>(Arrays.asList(
                            DateTimeUtil.formatLocalDateTime(movement.getDate()),
                            movement.getItemType(),
                            module.getCode(),
                            module.getStatus(),
                            module.getDescription(),
                            module.getWeight(),
                            SessionManager.getInstance().getLoggedInUser().getUsername()
                    )));
                }
            }

        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tblModule.setModel(model);

        JTableHeader header = tblModule.getTableHeader();
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void setContainerTable() {
        Vector<String> columnNames = new Vector<>(Arrays.asList("Historial", "Tipo", "Codigo", "Estado", "Ubicación", "Llegada", "Operador"));
        Vector<Vector<Object>> data = new Vector<>();

        if (cbContainer.getSelectedItem() != null) {
            Integer idContainer = containerController.getIdContainerByCode(cbContainer.getSelectedItem().toString());
            List<MovementHistory> movements =  movementHistoryController.getHistoryByIdItem(idContainer, ItemType.CONTAINER.getLabel());
            Container container = containerController.getContainerByCode(cbContainer.getSelectedItem().toString());

            for (MovementHistory movement : movements) {
                data.add(new Vector<>(Arrays.asList(
                        DateTimeUtil.formatLocalDateTime(movement.getDate()),
                        movement.getItemType(),
                        container.getCode(),
                        container.getStatus(),
                        container.getLocation(),
                        DateTimeUtil.formatLocalDateTime(container.getArriveDate()),
                        SessionManager.getInstance().getLoggedInUser().getUsername()
                )));
            }
        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tblContainer.setModel(model);

        JTableHeader header = tblContainer.getTableHeader();
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 18));

    }

    // Populate the combo box with the containers get from database
    public void populateContainerComboBox() {

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

    // Show error dialogs
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Validación errores", JOptionPane.ERROR_MESSAGE);
    }

    public JPanel getPanel() {
        return reportPanel;
    }
}
