package View.Contents;

import Controller.ContainerController;
import Model.Container;
import Model.Status;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportView {

    private JPanel reportPanel;
    private JTextField txtFromDate;
    private JComboBox cbContainer;
    private JTable table1;
    private JTextField txtToDate;
    private JButton btnSearch;
    private ContainerController containerController;

    public ReportView() {
        containerController = new ContainerController();
        populateContainerComboBox();
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

    public JPanel getPanel() {
        return reportPanel;
    }
}
