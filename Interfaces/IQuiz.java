// IQuiz.java
public interface IQuiz {
    void loadQuestions();
    void submitAnswer(String answer);
    int getScore();
    void finishQuiz(UserProfile user);
}