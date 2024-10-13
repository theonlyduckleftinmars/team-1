package database;

import model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class PlayerManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/photon";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "student";


    public void insertPlayers(List<Player> players) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO players (id, codename, equipment_id) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (Player player : players) {
                    statement.setInt(1, player.getId());
                    statement.setString(2, player.getCodeName());
                    statement.setInt(3, player.getEquipmentId());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
