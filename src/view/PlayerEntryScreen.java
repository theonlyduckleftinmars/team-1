package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerEntryScreen {
    public void display() {
        JFrame frame = new JFrame("Laser Tag - Player Entry");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("Enter Player ID:");
        JTextField idField = new JTextField();

        JLabel codeNameLabel = new JLabel("Enter Code Name:");
        JTextField codeNameField = new JTextField();

        JLabel equipmentLabel = new JLabel("Enter Equipment ID:");
        JTextField equipmentField = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String codeName = codeNameField.getText();
                String equipmentId = equipmentField.getText();
                JOptionPane.showMessageDialog(frame,
                        "Player added: ID=" + id + ", Code Name=" + codeName + ", Equipment ID=" + equipmentId);
            }
        });

        frame.add(idLabel);
        frame.add(idField);
        frame.add(codeNameLabel);
        frame.add(codeNameField);
        frame.add(equipmentLabel);
        frame.add(equipmentField);
        frame.add(submitButton);

        frame.setVisible(true);
    }
}
