// Updated QuizModule.java to properly implement IQuiz
import java.util.ArrayList;
import java.util.List;

public class QuizModule implements IQuiz {
    private List<String> questions;
    private int score;
    private List<String> userAnswers;
    private int currentQuestionIndex;

    public QuizModule() {
        this.questions = new ArrayList<>();
        this.userAnswers = new ArrayList<>();
        this.score = 0;
        this.currentQuestionIndex = 0;
    }

    @Override
    public void loadQuestions() {
        questions.clear();
        // Load mental health awareness questions
        questions.add("What is mindfulness?");
        questions.add("Which of the following is a symptom of anxiety?");
        questions.add("What is the first step in managing stress?");
        questions.add("True or False: Mental health is just as important as physical health");
        questions.add("What should you do if someone is having a panic attack?");
    }

    @Override
    public void submitAnswer(String answer) {
        if (currentQuestionIndex < questions.size()) {
            userAnswers.add(answer);
            
            // Simple scoring logic - you can make this more sophisticated
            if (isCorrectAnswer(currentQuestionIndex, answer)) {
                score += 10;
            }
            currentQuestionIndex++;
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void finishQuiz(UserProfile user) {
        user.updateScore(score);
        
        // Award badges based on performance
        if (score >= 40) {
            user.addBadge("Mental Health Expert");
        } else if (score >= 30) {
            user.addBadge("Mental Health Advocate");
        } else if (score >= 20) {
            user.addBadge("Mental Health Learner");
        } else {
            user.addBadge("Mental Health Beginner");
        }
    }

    // Helper method for answer validation
    private boolean isCorrectAnswer(int questionIndex, String answer) {
        switch (questionIndex) {
            case 0: return answer.equalsIgnoreCase("Being present") || 
                          answer.equalsIgnoreCase("Awareness of the present moment");
            case 1: return answer.toLowerCase().contains("worry") || 
                          answer.toLowerCase().contains("fear") ||
                          answer.toLowerCase().contains("restless");
            case 2: return answer.toLowerCase().contains("identify") || 
                          answer.toLowerCase().contains("recognize");
            case 3: return answer.equalsIgnoreCase("true");
            case 4: return answer.toLowerCase().contains("calm") || 
                          answer.toLowerCase().contains("breathe");
            default: return false;
        }
    }

    // Additional getter methods for UI
    public List<String> getQuestions() {
        return new ArrayList<>(questions);
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    public String getCurrentQuestion() {
        if (hasMoreQuestions()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        userAnswers.clear();
    }
}