// Updated UserProfile.java to match UML
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

    // Getters to match UML
    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public List<String> getBadges() {
        return badges;
    }

    // Methods to match UML
    public void updateScore(int points) {
        this.score += points;
    }

    public String getProfile() {
        return "User: " + username + ", Score: " + score + ", Badges: " + badges;
    }

    // Setter methods if needed
    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    public void addBadge(String badge) {
        if (!this.badges.contains(badge)) {
            this.badges.add(badge);
        }
    }
}