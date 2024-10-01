package View.Contents;

import javax.swing.*;
import java.awt.*;

public class Home extends javax.swing.JPanel {

    public Home() {

        setLayout(new GridBagLayout());

        JLabel label = new JLabel("HOME");
        label.setFont(new Font("Arial", Font.BOLD, 36));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        add(label, gbc);

    }
}
