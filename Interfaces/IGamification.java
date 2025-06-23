// IGamification.java
import java.util.List;

public interface IGamification {
    void awardPoints(int points);
    List<String> getBadges();
    void addBadge(String badge);
    String generateBadge (int score);
}