import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MCQQuizScreen {
    private JFrame frame;
    private UserProfile user;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private boolean isTimerMode;
    private Timer timer;
    private int timeLeft = 15; // 15 seconds per question
    private JLabel timerLabel;
    private JLabel questionLabel;
    private JButton[] optionButtons;

    private String[] questions = {
        "What is mindfulness?",
        "Which is a symptom of anxiety?",
        "First step in stress management?",
        "True/False: Mental health is as important as physical health.",
        "What should you do if someone panics?"
    };

    private String[][] options = {
        {"Being present", "Ignoring", "Overthinking", "Sleeping"},
        {"Joy", "Worry", "Calmness", "Relaxation"},
        {"Avoid stress", "Ignore stress", "Identify stressors", "Sleep it off"},
        {"True", "False", "", ""},
        {"Run away", "Calm them", "Yell", "Panic as well"}
    };

    private String[] correctAnswers = {
        "Being present",
        "Worry",
        "Identify stressors",
        "True",
        "Calm them"
    };

    public MCQQuizScreen(UserProfile user, boolean isTimerMode) {
        this.user = user;
        this.isTimerMode = isTimerMode;

        frame = new JFrame("MCQ Quiz");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(200, 162, 200));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            optionButtons[i].addActionListener(new OptionButtonListener());
            optionsPanel.add(optionButtons[i]);
        }

        panel.add(optionsPanel, BorderLayout.CENTER);

        timerLabel = new JLabel("", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(timerLabel, BorderLayout.SOUTH);

        frame.add(panel);
        displayQuestion();
        frame.setVisible(true);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);

            for (int i = 0; i < 4; i++) {
                if (!options[currentQuestionIndex][i].isEmpty()) {
                    optionButtons[i].setText(options[currentQuestionIndex][i]);
                    optionButtons[i].setVisible(true);
                } else {
                    optionButtons[i].setVisible(false);
                }
            }

            if (isTimerMode) {
                timeLeft = 15;
                timerLabel.setText("Time Left: " + timeLeft + "s");

                timer = new Timer(1000, e -> {
                    timeLeft--;
                    timerLabel.setText("Time Left: " + timeLeft + "s");
                    if (timeLeft <= 0) {
                        timer.stop();
                        processAnswer(""); // Time's up, empty answer
                    }
                });
                timer.start();
            } else {
                timerLabel.setText(""); // No timer text
            }

        } else {
            showResult();
        }
    }

    private void processAnswer(String answer) {
        if (timer != null) timer.stop();

        if (answer.equals(correctAnswers[currentQuestionIndex])) {
            score += 20; // Example: 20 points per correct
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private void showResult() {
        int stars = 0;
        String message = "";

        int percent = (score * 100) / (questions.length * 20);

        if (percent >= 80) { message = "Outstanding!"; stars = 5; }
        else if (percent >= 60) { message = "That's good!"; stars = 4; }
        else if (percent >= 40) { message = "Good try!"; stars = 3; }
        else if (percent >= 20) { message = "You can do better!"; stars = 2; }
        else { message = "Don't give up!"; stars = 1; }

        StringBuilder starDisplay = new StringBuilder();
        for (int i = 0; i < stars; i++) starDisplay.append("⭐");

        JOptionPane.showMessageDialog(frame,
                user.getUsername() + ", Congratulations!\n" +
                "Score: " + score + " / " + (questions.length * 20) + "\n" +
                "You received: " + starDisplay + "\n" + message);

        frame.dispose();
        new OptionScreen(user);
    }

    private class OptionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timer != null) timer.stop();
            JButton clickedButton = (JButton) e.getSource();
            processAnswer(clickedButton.getText());
        }
    }
}
