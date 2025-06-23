import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizScreen {
    private JFrame frame;
    private JLabel questionLabel, timerLabel;
    private JButton[] optionButtons;
    private QuizModule quizModule;
    private UserProfile user;
    private int timeLeft = 15; // MCQ timer: 15s
    private Timer timer;
    private boolean timerMode;

    public QuizScreen(UserProfile user, boolean timerMode, boolean isMCQ) {
        this.user = user;
        this.timerMode = timerMode;
        this.quizModule = new QuizModule();
        quizModule.loadQuestions(); // Assuming MCQ only for this screen
        createUI();
        if (timerMode) startTimer();
    }

    private void createUI() {
        frame = new JFrame("MCQ Quiz");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(200, 162, 200));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].addActionListener(new OptionButtonListener());
            buttonPanel.add(optionButtons[i]);
        }
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
                submitAnswer("");
            }
        });
        timer.start();
    }

    private void displayNextQuestion() {
        if (quizModule.hasMoreQuestions()) {
            questionLabel.setText("Q" + (quizModule.getCurrentQuestionIndex() + 1) + ": " + quizModule.getCurrentQuestion());
            optionButtons[0].setText("Option A");
            optionButtons[1].setText("Option B");
            optionButtons[2].setText("Option C");
            optionButtons[3].setText("Option D");
            timeLeft = 15;
            if (timerMode) timer.restart();
        } else {
            showResult();
        }
    }

    private void submitAnswer(String answer) {
        quizModule.submitAnswer(answer);
        displayNextQuestion();
    }

    private void showResult() {
        int correct = quizModule.getCorrectAnswers();
        int stars = (int) Math.ceil(correct / 4.0); // 20/20 => 5 stars
        if (stars > 5) stars = 5;

        String message;
        double percent = (correct / 20.0) * 100;

        if (percent >= 80) message = "Outstanding!";
        else if (percent >= 60) message = "That's good!";
        else if (percent >= 40) message = "Good try!";
        else if (percent >= 20) message = "You can do better!";
        else message = "Don't give up!";

        StringBuilder starDisplay = new StringBuilder();
        for (int i = 0; i < stars; i++) starDisplay.append("⭐");

        JOptionPane.showMessageDialog(frame,
                "Congratulations, " + user.getUsername() + "!\n" +
                        "Score: " + correct + "/20\n" +
                        "Stars: " + starDisplay + "\n" + message);

        frame.dispose();
        new OptionScreen(user);
    }

    private class OptionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (timerMode && timer != null) timer.stop();
            JButton clicked = (JButton) e.getSource();
            submitAnswer(clicked.getText());
        }
    }
}
