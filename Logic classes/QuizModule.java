import java.util.ArrayList;
import java.util.List;

public class QuizModule {
    private List<String> questions;
    private List<String> tfQuestions;
    private int score;
    private int tfScore;
    private List<String> userAnswers;
    private int currentQuestionIndex;
    private int currentTFQuestionIndex;

    public QuizModule() {
        this.questions = new ArrayList<>();
        this.tfQuestions = new ArrayList<>();
        this.userAnswers = new ArrayList<>();
        this.score = 0;
        this.tfScore = 0;
        this.currentQuestionIndex = 0;
        this.currentTFQuestionIndex = 0;
    }

    // MCQ Methods
    public void loadQuestions() {
        questions.clear();
        questions.add("What is mindfulness?");
        questions.add("Which is a symptom of anxiety?");
        questions.add("What is the first step in stress management?");
        questions.add("True/False: Mental health is as important as physical health");
        questions.add("What to do during a panic attack?");
        // You can add more MCQ questions to reach 20
    }

    public void submitAnswer(String answer) {
        if (currentQuestionIndex < questions.size()) {
            userAnswers.add(answer);
            if (isCorrectAnswer(currentQuestionIndex, answer)) {
                score++;
            }
            currentQuestionIndex++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getCorrectAnswers() {
        return score;
    }

    public void finishQuiz(UserProfile user) {
        user.updateScore(score);
    }

    private boolean isCorrectAnswer(int index, String answer) {
        switch (index) {
            case 0: return answer.equalsIgnoreCase("Option A");
            case 1: return answer.equalsIgnoreCase("Option B");
            case 2: return answer.equalsIgnoreCase("Option C");
            case 3: return answer.equalsIgnoreCase("Option A"); // For True/False as MCQ
            case 4: return answer.equalsIgnoreCase("Option D");
            default: return false;
        }
    }

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

    // True/False Methods
    public void loadTFQuestions() {
        tfQuestions.clear();
        tfQuestions.add("True/False: Depression is a mental health disorder.");
        tfQuestions.add("True/False: Anxiety only affects adults.");
        tfQuestions.add("True/False: Regular exercise benefits mental health.");
        tfQuestions.add("True/False: Mental illness is rare.");
        tfQuestions.add("True/False: Talking to friends can help mental health.");
        // You can add more to make 10
    }

    public void submitTFAnswer(String answer) {
        if (currentTFQuestionIndex < tfQuestions.size()) {
            if (isCorrectTFAnswer(currentTFQuestionIndex, answer)) {
                tfScore++;
            }
            currentTFQuestionIndex++;
        }
    }

    private boolean isCorrectTFAnswer(int index, String answer) {
        switch (index) {
            case 0: return answer.equalsIgnoreCase("True");
            case 1: return answer.equalsIgnoreCase("False");
            case 2: return answer.equalsIgnoreCase("True");
            case 3: return answer.equalsIgnoreCase("False");
            case 4: return answer.equalsIgnoreCase("True");
            default: return false;
        }
    }

    public int getTFCorrectAnswers() {
        return tfScore;
    }

    public int getCurrentTFQuestionIndex() {
        return currentTFQuestionIndex;
    }

    public boolean hasMoreTFQuestions() {
        return currentTFQuestionIndex < tfQuestions.size();
    }

    public String getCurrentTFQuestion() {
        if (hasMoreTFQuestions()) {
            return tfQuestions.get(currentTFQuestionIndex);
        }
        return null;
    }
}
