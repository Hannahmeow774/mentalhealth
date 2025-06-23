import java.util.ArrayList;
import java.util.List;

public class QuizModule implements IQuiz {
    private List<String> questions;
    private int score;

    public QuizModule() {
        questions = new ArrayList<>();
        score = 0;
    }

    public void loadQuestions() {
        questions.add("What is mindfulness?");
    }

    public void submitAnswer(String answer) {
        if (answer.equalsIgnoreCase("Being present")) {
            score += 10;
        }
    }

    public int getScore() {
        return score;
    }

    public void finishQuiz(UserProfile user) {
        user.updateScore(score);
    }
}

