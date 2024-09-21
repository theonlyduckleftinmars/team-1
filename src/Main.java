import view.PlayerEntryScreen;
import view.SplashScreen;

public class Main {
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.display();

        PlayerEntryScreen playerEntryScreen = new PlayerEntryScreen();
        playerEntryScreen.display();
    }
}
