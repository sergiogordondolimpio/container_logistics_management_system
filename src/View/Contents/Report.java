package View.Contents;

import javax.swing.*;
import java.awt.*;

public class Report extends javax.swing.JPanel {

    public Report() {

        setLayout(new GridBagLayout());

        JLabel label = new JLabel("Reporte");
        label.setFont(new Font("Arial", Font.BOLD, 36));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        add(label, gbc);

    }
}
