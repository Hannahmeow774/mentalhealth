import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MCQQuizScreen {
    private JFrame frame;
    private UserProfile user;
    private boolean timerMode;
    private int score = 0;
    private int questionIndex = 0;
    private String[] questions = {
            "What is Mental Health?",
            "What is anxiety?",
            "Who can experience mental health issues?"
    };
    private String[][] options = {
            {"Physical fitness", "Emotional well-being", "Financial stability", "None of the above"},
            {"A type of exercise", "A mental state of worry", "A healthy habit", "A disease like cold"},
            {"Only adults", "Only teenagers", "Anyone regardless of age", "Only elderly"}
    };
    private int[] answers = {1, 1, 2}; // correct option indexes
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JLabel timerLabel;
    private Timer quizTimer;
    private int timePerQuestion = 15; // seconds
    private int timeLeft;

    public MCQQuizScreen(UserProfile user, boolean timerMode) {
        this.user = user;
        this.timerMode = timerMode;

        frame = new JFrame("MCQ Quiz");
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        optionsPanel.setOpaque(false);
        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setOpaque(false);
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

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
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options[questionIndex][i]);
            }
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

        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                if (i == answers[questionIndex]) {
                    score += 10;
                }
            }
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
        if (score >= 30) {
            motivation = "Excellent work!";
        } else if (score >= 20) {
            motivation = "Good job!";
        } else {
            motivation = "Keep learning!";
        }

        JOptionPane.showMessageDialog(frame,
                message + "\n" + starRating + "\n" + motivation,
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);

        frame.dispose();
        new OptionScreen(user);
    }
}
