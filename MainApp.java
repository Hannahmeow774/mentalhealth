public class MainApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            UserProfile user = new UserProfile("Guest"); // or let user input name later
            new StartScreen(user);
        });
    }
}
