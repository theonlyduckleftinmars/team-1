package controller;

import database.PlayerManager;
import model.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class PlayerEntryController {

    private List<Player> players;
    private JFrame frame;

    public PlayerEntryController(List<Player> players, JFrame frame) {
        this.players = players;
        this.frame = frame;


        frame.getRootPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    submitPlayersToDatabase();
                }
            }
        });


        frame.requestFocusInWindow();
    }


    private void submitPlayersToDatabase() {
        if (players.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No players to submit!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PlayerManager playerManager = new PlayerManager();
        playerManager.insertPlayers(players);

        JOptionPane.showMessageDialog(frame, "Players submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
