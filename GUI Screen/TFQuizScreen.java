import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TFQuizScreen {
    private JFrame frame;
    private JLabel questionLabel, timerLabel;
    private JButton trueButton, falseButton;
    private QuizModule quizModule;
    private UserProfile user;
    private int timeLeft = 10; // 10s per question for T/F
    private Timer timer;
    private boolean timerMode;

    public TFQuizScreen(UserProfile user, boolean istimerMode) {
        this.user = user;
        this.timerMode = timerMode;
        this.quizModule = new QuizModule();
        quizModule.loadTFQuestions();
        createUI();
        if (timerMode) startTimer();
    }

    private void createUI() {
        frame = new JFrame("True/False Quiz");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(200, 162, 200));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        trueButton = new JButton("True");
        falseButton = new JButton("False");

        trueButton.addActionListener(e -> submitAnswer("True"));
        falseButton.addActionListener(e -> submitAnswer("False"));

        buttonPanel.add(trueButton);
        buttonPanel.add(falseButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        timerLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(timerLabel, BorderLayout.SOUTH);

        frame.add(panel);
        displayNextQuestion();
        frame.setVisible(true);
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                timer.stop();
                submitAnswer(""); // Auto-submit blank
            }
        });
        timer.start();
    }

    private void displayNextQuestion() {
        if (quizModule.hasMoreTFQuestions()) {
            questionLabel.setText("Q" + (quizModule.getCurrentTFQuestionIndex() + 1) + ": " + quizModule.getCurrentTFQuestion());
            timeLeft = 10;
            if (timerMode) timer.restart();
        } else {
            showResult();
        }
    }

    private void submitAnswer(String answer) {
        quizModule.submitTFAnswer(answer);
        displayNextQuestion();
    }

    private void showResult() {
        int correct = quizModule.getTFCorrectAnswers();
        int stars = (int) Math.ceil(correct / 2.0); // 10/10 => 5 stars
        if (stars > 5) stars = 5;

        String message;
        double percent = (correct / 10.0) * 100;

        if (percent >= 80) message = "Outstanding!";
        else if (percent >= 60) message = "That's good!";
        else if (percent >= 40) message = "Good try!";
        else if (percent >= 20) message = "You can do better!";
        else message = "Don't give up!";

        StringBuilder starDisplay = new StringBuilder();
        for (int i = 0; i < stars; i++) starDisplay.append("⭐");

        JOptionPane.showMessageDialog(frame,
                "Congratulations, " + user.getUsername() + "!\n" +
                        "Score: " + correct + "/10\n" +
                        "Stars: " + starDisplay + "\n" + message);

        frame.dispose();
        new OptionScreen(user);
    }
}
