package View.Contents;

import javax.swing.*;
import java.awt.*;

public class ConsultStatus extends javax.swing.JPanel {

    public ConsultStatus() {

        setLayout(new GridBagLayout());

        JLabel label = new JLabel("Consultar Estatus");
        label.setFont(new Font("Arial", Font.BOLD, 36));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        add(label, gbc);

    }

}