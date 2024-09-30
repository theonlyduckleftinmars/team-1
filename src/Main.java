import view.PlayerEntryScreen;
import view.SplashScreen;
//Dorothy Oliver code comment: sprint 2
//Display spalsh screen and player entry screen
public class Main {
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.display();

        PlayerEntryScreen playerEntryScreen = new PlayerEntryScreen();
        playerEntryScreen.display();
    }
}
