package View;

import View.Contents.DevanningView;
import View.Contents.HomeView;
import View.Contents.RegisterContainerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView {
    public JPanel mainPanel;
    private JButton btnHome;
    private JButton btnContainerRegister;
    private JButton btnDevanning;
    private JButton btnUnpacking;
    private JButton btnRevertOperation;
    private JButton btnReport;
    private JButton btnGetStatus;
    private JPanel contextPanel;

    public MenuView() {
        // Get the CardLayout from the contextPanel
        CardLayout cardLayout = (CardLayout) contextPanel.getLayout();
        addViews();

        // Add ActionListeners to the buttons to switch views
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contextPanel, "Home");
            }

        });

        btnContainerRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contextPanel, "ContainerRegister");
            }
        });

        btnDevanning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contextPanel, "Devanning");
            }
        });
    }

    public void addViews() {
        // Add views to the CardLayout
        contextPanel.add(new HomeView().getPanel(), "Home");
        contextPanel.add(new RegisterContainerView().getPanel(), "ContainerRegister");
        contextPanel.add(new DevanningView().getPanel(), "Devanning");
    }
}