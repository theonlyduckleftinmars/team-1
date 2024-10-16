package view;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayActionScreen {

    private static final Color DARK_BACKGROUND = new Color(45, 45, 45);
    private static final Color LIGHT_TEXT = new Color(200, 200, 200);

    private List<Player> greenTeamPlayers;
    private List<Player> redTeamPlayers;

    public PlayActionScreen(List<Player> greenTeamPlayers, List<Player> redTeamPlayers) {
        this.greenTeamPlayers = greenTeamPlayers;
        this.redTeamPlayers = redTeamPlayers;
    }

    public void display() {
        JFrame frame = new JFrame("Laser Tag - Play Action");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel teamPanel = new JPanel(new GridLayout(1, 2));

        JPanel greenTeamPanel = createTeamPanel("Green Team", greenTeamPlayers, Color.GREEN);
        JPanel redTeamPanel = createTeamPanel("Red Team", redTeamPlayers, Color.RED);

        teamPanel.add(greenTeamPanel);
        teamPanel.add(redTeamPanel);

        frame.add(teamPanel, BorderLayout.CENTER);
        frame.getContentPane().setBackground(DARK_BACKGROUND);
        frame.setVisible(true);
    }

    private JPanel createTeamPanel(String teamName, List<Player> players, Color teamColor) {
        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.setBorder(BorderFactory.createLineBorder(teamColor, 3));
        teamPanel.setBackground(DARK_BACKGROUND);

        JLabel teamLabel = new JLabel(teamName, SwingConstants.CENTER);
        teamLabel.setFont(new Font("Arial", Font.BOLD, 24));
        teamLabel.setOpaque(true);
        teamLabel.setBackground(teamColor);
        teamLabel.setForeground(Color.WHITE);
        teamPanel.add(teamLabel, BorderLayout.NORTH);

        JPanel playerListPanel = new JPanel(new GridLayout(players.size(), 1, 5, 5));
        playerListPanel.setBackground(DARK_BACKGROUND);

        for (Player player : players) {
            JLabel playerLabel = new JLabel("ID: " + player.getId() + " | Codename: " + player.getCodeName());
            playerLabel.setForeground(LIGHT_TEXT);
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            playerListPanel.add(playerLabel);
        }

        teamPanel.add(playerListPanel, BorderLayout.CENTER);

        return teamPanel;
    }
}
