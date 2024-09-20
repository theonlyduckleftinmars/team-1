package view;

import javax.swing.*;
import java.awt.*;

public class SplashScreen {
    public void display() {
        JFrame frame = new JFrame("Laser Tag - Splash Screen");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to Laser Tag!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label);

        frame.setVisible(true);

        // Display for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        frame.dispose(); // Close the splash screen
    }
}
