import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private String username;
    private int score;
    private List<String> badges;

    public UserProfile(String username) {
        this.username = username;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    public void updateScore(int points) {
        score += points;
    }

    public String getProfile() {
        return "User: " + username + ", Score: " + score + ", Badges: " + badges;
    }
}
