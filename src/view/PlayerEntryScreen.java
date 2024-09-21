package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerEntryScreen {

    private static final int NUM_PLAYERS = 15;
    private static final Color DARK_BACKGROUND = new Color(45, 45, 45);
    private static final Color DARKER_BACKGROUND = new Color(30, 30, 30);
    private static final Color LIGHT_TEXT = new Color(200, 200, 200);

    public void display() {
        JFrame frame = new JFrame("Laser Tag - Player Entry (Dark Theme)");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel greenTeamPanel = createTeamPanel("Green Team", Color.GREEN);
        JPanel redTeamPanel = createTeamPanel("Red Team", Color.RED);

        JPanel teamsPanel = new JPanel(new GridLayout(1, 2));
        teamsPanel.add(greenTeamPanel);
        teamsPanel.add(redTeamPanel);
        teamsPanel.setBackground(DARK_BACKGROUND);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(DARKER_BACKGROUND);
        submitButton.setForeground(LIGHT_TEXT);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(frame, "Players submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(teamsPanel, BorderLayout.CENTER);
        frame.add(submitButton, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(DARK_BACKGROUND);
        frame.setVisible(true);
    }

    private JPanel createTeamPanel(String teamName, Color teamColor) {
        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.setBorder(BorderFactory.createLineBorder(teamColor, 3));
        teamPanel.setBackground(DARK_BACKGROUND);

        JLabel teamLabel = new JLabel(teamName, SwingConstants.CENTER);
        teamLabel.setFont(new Font("Arial", Font.BOLD, 24));
        teamLabel.setOpaque(true);
        teamLabel.setBackground(teamColor);
        teamLabel.setForeground(Color.WHITE);
        teamPanel.add(teamLabel, BorderLayout.NORTH);

        JPanel playerInputPanel = new JPanel(new GridLayout(NUM_PLAYERS, 1, 5, 5));
        playerInputPanel.setBackground(DARK_BACKGROUND);

        for (int i = 0; i < NUM_PLAYERS; i++) {
            JPanel row = new JPanel(new GridLayout(1, 3, 5, 5));
            row.setBackground(DARK_BACKGROUND);

            JTextField playerIdField = createDarkInputField("Player ID");
            JTextField codeNameField = createDarkInputField("Code Name");
            JTextField equipmentIdField = createDarkInputField("Equipment ID");

            row.add(playerIdField);
            row.add(codeNameField);
            row.add(equipmentIdField);

            playerInputPanel.add(row);
        }

        teamPanel.add(playerInputPanel, BorderLayout.CENTER);

        return teamPanel;
    }

    private JTextField createDarkInputField(String title) {
        JTextField textField = new JTextField();
        textField.setBackground(DARKER_BACKGROUND);
        textField.setForeground(LIGHT_TEXT);
        textField.setCaretColor(LIGHT_TEXT);
        textField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(LIGHT_TEXT, 1), title, 0, 0, null, LIGHT_TEXT));
        return textField;
    }
}
