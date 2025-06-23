// Updated Gamification.java to match UML exactly
import java.util.ArrayList;
import java.util.List;

public class Gamification implements IGamification {
    private int points;
    private List<String> badges;

    public Gamification() {
        this.points = 0;
        this.badges = new ArrayList<>();
    }

    @Override
    public void awardPoints(int points) {
        this.points += points;
    }

    @Override
    public List<String> getBadges() {
        return new ArrayList<>(badges); // Return copy to prevent external modification
    }

    @Override
    public void addBadge(String badge) {
        if (!badges.contains(badge)) {
            badges.add(badge);
        }
    }

    @Override
    public String generateBadge(int score) {
        if (score >= 50) return "Mental Health Champion";
        else if (score >= 40) return "Mental Health Expert";
        else if (score >= 30) return "Mental Health Advocate";
        else if (score >= 20) return "Mental Health Learner";
        else if (score >= 10) return "Mindfulness Starter";
        else return "Mental Health Beginner";
    }

    // Additional getter for points (if needed by UML)
    public int getPoints() {
        return points;
    }

    // Method to reset points (utility method)
    public void resetPoints() {
        this.points = 0;
    }

    // Method to set points directly (if needed)
    public void setPoints(int points) {
        this.points = points;
    }
}