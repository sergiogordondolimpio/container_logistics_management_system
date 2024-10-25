package view;

import view.Contents.DevanningView;
import view.Contents.HomeView;
import view.Contents.RegisterContainerView;
import view.Contents.ReportView;

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
    private DevanningView devanningView;
    private ReportView reportView;

    public MenuView() {
        devanningView = new DevanningView();
        reportView = new ReportView();

        contextPanel.setLayout(new CardLayout());

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
                devanningView.populateContainerComboBox();
                cardLayout.show(contextPanel, "Devanning");
            }
        });

        btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportView.populateContainerComboBox();
                cardLayout.show(contextPanel, "Report");
            }
        });
    }

    // Add views to the CardLayout
    public void addViews() {
        contextPanel.add(new HomeView().getPanel(), "Home");
        contextPanel.add(new RegisterContainerView().getPanel(), "ContainerRegister");
        contextPanel.add(devanningView.getPanel(), "Devanning");
        contextPanel.add(reportView.getPanel(), "Report");
    }
}

