// made by Bashir

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MultipleChoiceScreen {
    JFrame frame;
    JPanel panel;
    JLabel questionLabel;
    JRadioButton[] optionButtons;
    ButtonGroup group;
    JButton submitButton, nextButton, backButton;

    int currentQuestion = 0;
    int score = 0;  // ✅ Track correct answers
    boolean answered = false;

    String[] questions = {
            "1. Which of the following is a mental health disorder?",
            "2. Which of the following can be a symptom of depression?",
            "3. Which profession can help diagnose and treat mental health disorders?",
            "4. What is the term for excessive fear of social situations?",
            "5. What is the primary chemical in the brain linked to mood regulation?",
            "6. Which of the following is NOT a healthy coping strategy for stress?",
            "7. Which age group can experience mental health issues?",
            "8. Which of these disorders involves extreme mood swings?",
            "9. What is the benefit of mindfulness and meditation?",
            "10. Which of the following is a common myth about mental illness?"
    };

    String[][] options = {
            {"Flu", "Anxiety", "Cold", "Fever"},
            {"Headache", "Shortness of breath", "Loss of interest in activities", "Blurred vision"},
            {"Dentist", "Psychiatrist", "Optometrist", "Cardiologist"},
            {"Claustrophobia", "Agoraphobia", "Social anxiety disorder", "Panic disorder"},
            {"Insulin", "Hemoglobin", "Serotonin", "Adrenaline"},
            {"Exercise", "Talking to a friend", "Excessive drinking", "Journaling"},
            {"Only adults", "Only teenagers", "Only elderly", "All age groups"},
            {"OCD", "Bipolar disorder", "Schizophrenia", "Anorexia"},
            {"Causes memory loss", "Increases physical strength", "Reduces stress", "Makes you tired"},
            {"It can be treated", "It's caused by weakness", "Therapy can help", "It affects many people"}
    };

    int[] correctAnswers = {1, 2, 1, 2, 2, 2, 3, 1, 2, 1};

    public MultipleChoiceScreen() {
        frame = new JFrame("Multiple Choice Quiz");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(224, 228, 255));

        questionLabel = new JLabel();
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        optionButtons = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            group.add(optionButtons[i]);
        }

        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(this::checkAnswer);

        nextButton = new JButton("Next");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(e -> {
            if (!answered) {
                JOptionPane.showMessageDialog(frame, "Please submit your answer first.");
                return;
            }

            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                answered = false;
                showQuestion();
            } else {
                JOptionPane.showMessageDialog(frame, "Quiz completed! Your score: " + score + " out of " + questions.length);
            }
        });

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            if (currentQuestion > 0) {
                currentQuestion--;
                showQuestion();
            } else {
                frame.dispose();
                new QuizTopicScreen(); // Back to topic selection
            }
        });

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(questionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        for (int i = 0; i < 4; i++) {
            panel.add(optionButtons[i]);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(submitButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(nextButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backButton);

        frame.add(panel);
        showQuestion();
        frame.setVisible(true);
    }

    private void showQuestion() {
        group.clearSelection();
        answered = false;

        // ✅ Auto-wrap question using HTML
        String questionHtml = "<html><body style='width: 310px'>" + questions[currentQuestion] + "</body></html>";
        questionLabel.setText(questionHtml);

        // ✅ Auto-wrap each option using HTML
        for (int i = 0; i < 4; i++) {
            String optionHtml = "<html><body style='width: 200px'>" + options[currentQuestion][i] + "</body></html>";
            optionButtons[i].setText(optionHtml);
        }
    }


    private void checkAnswer(ActionEvent e) {
        int selected = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selected = i;
                break;
            }
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an answer.");
        } else {
            answered = true;
            if (selected == correctAnswers[currentQuestion]) {
                JOptionPane.showMessageDialog(frame, "Correct!");
                score++; // ✅ Increment score
            } else {
                String correctText = options[currentQuestion][correctAnswers[currentQuestion]];
                JOptionPane.showMessageDialog(frame, "Incorrect. The correct answer is: " + correctText);
            }
        }
    }
}
