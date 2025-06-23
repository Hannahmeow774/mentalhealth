import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TFQuizScreen {
    private JFrame frame;
    private UserProfile user;
    private boolean timerMode;
    private int score = 0;
    private int questionIndex = 0;
    private String[] questions = {
            "Mental health affects how we think and feel. (True/False)",
            "Only teenagers have mental health challenges. (True/False)",
            "Stress can impact mental health. (True/False)"
    };
    private boolean[] answers = {true, false, true};
    private JLabel questionLabel;
    private JRadioButton trueButton, falseButton;
    private ButtonGroup optionsGroup;
    private JLabel timerLabel;
    private Timer quizTimer;
    private int timePerQuestion = 10; // seconds
    private int timeLeft;

    public TFQuizScreen(UserProfile user, boolean timerMode) {
        this.user = user;
        this.timerMode = timerMode;

        frame = new JFrame("True/False Quiz");
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        optionsPanel.setOpaque(false);
        trueButton = new JRadioButton("True");
        falseButton = new JRadioButton("False");
        trueButton.setOpaque(false);
        falseButton.setOpaque(false);
        trueButton.setFont(new Font("Arial", Font.PLAIN, 14));
        falseButton.setFont(new Font("Arial", Font.PLAIN, 14));
        optionsGroup = new ButtonGroup();
        optionsGroup.add(trueButton);
        optionsGroup.add(falseButton);
        optionsPanel.add(trueButton);
        optionsPanel.add(falseButton);

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);

        timerLabel = new JLabel("");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(timerLabel);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> checkAnswer());
        bottomPanel.add(nextButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        loadQuestion();
    }

    private void loadQuestion() {
        if (questionIndex < questions.length) {
            questionLabel.setText("Q" + (questionIndex + 1) + ": " + questions[questionIndex]);
            optionsGroup.clearSelection();

            if (timerMode) {
                timeLeft = timePerQuestion;
                timerLabel.setText("Time left: " + timeLeft + "s");
                if (quizTimer != null) quizTimer.stop();
                quizTimer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        timeLeft--;
                        timerLabel.setText("Time left: " + timeLeft + "s");
                        if (timeLeft <= 0) {
                            quizTimer.stop();
                            checkAnswer();
                        }
                    }
                });
                quizTimer.start();
            } else {
                timerLabel.setText("");
            }
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        if (timerMode && quizTimer != null) quizTimer.stop();

        boolean selectedAnswer = trueButton.isSelected();
        if (selectedAnswer == answers[questionIndex]) {
            score += 10;
        }
        questionIndex++;
        loadQuestion();
    }

    private void showResult() {
        String message = "Well done, " + user.getUsername() + "! Your Score: " + score;
        int stars = score / 10;

        String starRating = "Stars: ";
        for (int i = 0; i < stars; i++) {
            starRating += "⭐";
        }

        String motivation;
        if (score >= 20) {
            motivation = "Fantastic effort!";
        } else if (score >= 10) {
            motivation = "Good try!";
        } else {
            motivation = "Keep practicing!";
        }

        JOptionPane.showMessageDialog(frame,
                message + "\n" + starRating + "\n" + motivation,
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);

        frame.dispose();
        new OptionScreen(user);
    }
}
