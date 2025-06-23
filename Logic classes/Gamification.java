import java.util.ArrayList;
import java.util.List;

public class Gamification implements IGamification {
    private int points;
    private List<String> badges;

    public Gamification() {
        points = 0;
        badges = new ArrayList<>();
    }

    public void awardPoints(int points) {
        this.points += points;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void addBadge(String badge) {
        badges.add(badge);
    }

    public String generateBadge(int score) {
        if (score >= 10) return "Mindfulness Starter";
        else return "Beginner";
    }
}
