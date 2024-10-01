package View;

import View.Contents.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Menu extends JFrame {

    public Menu() {
        initializeLayout();
    }

    // Main function to initialize the layout of the application
    private void initializeLayout() {
        JFrame frame = new JFrame("Sistema de Gestión de Contenedores y Logística");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 900);
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = createContentPanel();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createTitlePanel(), BorderLayout.NORTH);
        topPanel.add(createNavBarPanel(contentPanel), BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Creates the title panel
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(800, 100));

        JLabel titleLabel = new JLabel("Sistema de Gestión de Contenedores y Logística", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        titlePanel.add(titleLabel, BorderLayout.CENTER);

        return titlePanel;
    }

    // Creates the navbar with buttons, takes the content panel as a parameter for switching content
    private JPanel createNavBarPanel(JPanel contentPanel) {
        JPanel navbarPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 20;

        // Navigation buttons
        String[] menuItems = {"Home", "Registrar container", "Devanning", "Unpacking", "Revertir operación", "Reporte", "Consultar estado"};

        for (int i = 0; i < menuItems.length; i++) {
            JButton button = new JButton(menuItems[i]);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            gbc.gridx = i;
            navbarPanel.add(button, gbc);

            // Action listener to switch content panels when a button is pressed
            int finalI = i;
            button.addActionListener(e -> {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, menuItems[finalI]);
            });
        }

        return navbarPanel;
    }

    // Creates the content panel with CardLayout for switching content
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new CardLayout());

        // Map to hold content panels for different sections
        Map<String, JPanel> contentMap = new LinkedHashMap<>();
        contentMap.put("Home", new Home());
        contentMap.put("Registrar container", new RegisterContainer());
        contentMap.put("Devanning", new Devanning());
        contentMap.put("Unpacking", new Unpacking());
        contentMap.put("Revertir operación", new Reverse());
        contentMap.put("Reporte", new Report());
        contentMap.put("Consultar estado", new ConsultStatus());

        // Add each content panel to the contentPanel with the respective name
        for (Map.Entry<String, JPanel> entry : contentMap.entrySet()) {
            contentPanel.add(entry.getValue(), entry.getKey());
        }

        return contentPanel;
    }

}
