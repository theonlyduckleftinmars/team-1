package view;

import database.PlayerManager;
import model.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class PlayerEntryScreen {

    private static final int NUM_PLAYERS = 15;
    private JLabel[][] greenTeamFields = new JLabel[NUM_PLAYERS][2];
    private JLabel[][] redTeamFields = new JLabel[NUM_PLAYERS][2];

    private int greenTeamIndex = 0;
    private int redTeamIndex = 0;

    private Set<String> playerIds = new HashSet<>();
    private Set<String> codeNames = new HashSet<>();

    public void display() {
        JFrame frame = new JFrame("Laser Tag - Photon");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JPanel greenTeamPanel = createTeamPanel("Green Team", Color.GREEN, greenTeamFields);
        JPanel redTeamPanel = createTeamPanel("Red Team", Color.RED, redTeamFields);


        JPanel teamsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        teamsPanel.add(greenTeamPanel);
        teamsPanel.add(redTeamPanel);
        teamsPanel.setBackground(new Color(45, 45, 45));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


        JButton enterPlayerButton = new JButton("Enter New Player");
        enterPlayerButton.setBackground(new Color(30, 30, 30));
        enterPlayerButton.setForeground(new Color(200, 200, 200));
        enterPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPlayerForm(frame);
            }
        });

        JButton submitPlayersButton = new JButton("Submit Players");
        submitPlayersButton.setBackground(new Color(30, 30, 30));
        submitPlayersButton.setForeground(new Color(200, 200, 200));
        submitPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPlayersToDatabase(frame);
            }
        });

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(enterPlayerButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(submitPlayersButton);
        buttonPanel.add(Box.createHorizontalGlue());


        frame.add(teamsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.getContentPane().setBackground(new Color(45, 45, 45));
        frame.setVisible(true);
    }


    private JPanel createTeamPanel(String teamName, Color teamColor, JLabel[][] playerFields) {
        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.setBorder(BorderFactory.createLineBorder(teamColor, 3));

        JLabel teamLabel = new JLabel(teamName, SwingConstants.CENTER);
        teamLabel.setFont(new Font("Arial", Font.BOLD, 24));
        teamLabel.setOpaque(true);
        teamLabel.setBackground(teamColor);
        teamLabel.setForeground(Color.WHITE);
        teamPanel.add(teamLabel, BorderLayout.NORTH);

        JPanel playerInputPanel = new JPanel(new GridLayout(NUM_PLAYERS, 1, 10, 10));
        playerInputPanel.setBackground(new Color(45, 45, 45));

        for (int i = 0; i < NUM_PLAYERS; i++) {
            JPanel row = new JPanel(new GridLayout(1, 2, 5, 5));
            row.setBackground(new Color(45, 45, 45));


            JLabel playerIdLabel = createDisplayLabel("Player ID");
            JLabel codeNameLabel = createDisplayLabel("Code Name");

            row.add(playerIdLabel);
            row.add(codeNameLabel);

            playerFields[i][0] = playerIdLabel;
            playerFields[i][1] = codeNameLabel;

            playerInputPanel.add(row);
        }

        teamPanel.add(playerInputPanel, BorderLayout.CENTER);

        return teamPanel;
    }


    private JLabel createDisplayLabel(String placeholder) {
        JLabel label = new JLabel(placeholder, SwingConstants.CENTER);
        label.setBackground(new Color(30, 30, 30));   // Dark background
        label.setForeground(new Color(200, 200, 200)); // Light text
        label.setOpaque(true);
        label.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                placeholder, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12), new Color(200, 200, 200)
        ));
        return label;
    }


    private void openPlayerForm(JFrame parentFrame) {
        JFrame formFrame = new JFrame("Enter New Player");
        formFrame.setSize(400, 300);
        formFrame.setLocationRelativeTo(parentFrame);
        formFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField playerIdField = new JTextField();
        JTextField codeNameField = new JTextField();


        playerIdField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char inputChar = e.getKeyChar();
                if (!Character.isDigit(inputChar)) {
                    e.consume(); // Prevent non-numeric characters
                    JOptionPane.showMessageDialog(formFrame, "Player ID must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        String[] teams = {"Green", "Red"};
        JComboBox<String> teamSelection = new JComboBox<>(teams);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(30, 30, 30));
        submitButton.setForeground(new Color(200, 200, 200));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerId = playerIdField.getText();
                String codeName = codeNameField.getText();
                String selectedTeam = (String) teamSelection.getSelectedItem();


                if (playerIds.contains(playerId)) {
                    JOptionPane.showMessageDialog(formFrame, "Player ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (codeNames.contains(codeName)) {
                    JOptionPane.showMessageDialog(formFrame, "Code Name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                if (selectedTeam.equals("Green") && greenTeamIndex < NUM_PLAYERS) {
                    greenTeamFields[greenTeamIndex][0].setText(playerId);
                    greenTeamFields[greenTeamIndex][1].setText(codeName);
                    greenTeamIndex++;
                } else if (selectedTeam.equals("Red") && redTeamIndex < NUM_PLAYERS) {
                    redTeamFields[redTeamIndex][0].setText(playerId);
                    redTeamFields[redTeamIndex][1].setText(codeName);
                    redTeamIndex++;
                }

                playerIds.add(playerId);
                codeNames.add(codeName);

                formFrame.dispose();
            }
        });

        formFrame.add(new JLabel("Player ID:"));
        formFrame.add(playerIdField);
        formFrame.add(new JLabel("Code Name:"));
        formFrame.add(codeNameField);
        formFrame.add(new JLabel("Team:"));
        formFrame.add(teamSelection);
        formFrame.add(new JLabel());
        formFrame.add(submitButton);

        formFrame.setVisible(true);
    }


    private void submitPlayersToDatabase(JFrame frame) {
        if (playerIds.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No players to submit!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PlayerManager playerManager = new PlayerManager();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            if (greenTeamFields[i][0].getText() != null && !greenTeamFields[i][0].getText().equals("Player ID")) {
                playerManager.insertPlayer(new Player(Integer.parseInt(greenTeamFields[i][0].getText()), greenTeamFields[i][1].getText()));
            }
            if (redTeamFields[i][0].getText() != null && !redTeamFields[i][0].getText().equals("Player ID")) {
                playerManager.insertPlayer(new Player(Integer.parseInt(redTeamFields[i][0].getText()), redTeamFields[i][1].getText()));
            }
        }

        clearPlayerEntries();
        playerIds.clear();
        codeNames.clear();

        JOptionPane.showMessageDialog(frame, "Players submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearPlayerEntries() {
        for (int i = 0; i < NUM_PLAYERS; i++) {
            greenTeamFields[i][0].setText("Player ID");
            greenTeamFields[i][1].setText("Code Name");
            redTeamFields[i][0].setText("Player ID");
            redTeamFields[i][1].setText("Code Name");
        }
        greenTeamIndex = 0;
        redTeamIndex = 0;
    }
}

