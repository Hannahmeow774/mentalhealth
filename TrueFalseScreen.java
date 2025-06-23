// made by Scarlett

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TrueFalseScreen {
    JFrame frame;
    JLabel questionLabel;
    JRadioButton trueOption, falseOption;
    JButton submitButton;
    ButtonGroup group;

    int currentQuestion = 0;
    int score = 0;

    // 10 true/false questions
    String[] questions = {
            "Mental health refers to a person’s emotional, psychological, and social well-being.",
            "Common disorders like depression DOES NOT affect our daily lives.",
            "Depression symptoms include difficulty in concentrating.",
            "Mental health problems are rare or a sign of weakness.",
            "Healthy coping strategies include exercising.",
            "Professional support CANNOT guide personal growth and recovery.",
            "People with mental illness are violent.",
            "Workplaces should not promote mental health for better productivity.",
            "Sleep affects mental health.",
            "We should pressure people with mental illness to seek professional help."
    };

    boolean[] answers = {
            true,
            false,
            true,
            false,
            true,
            false,
            false,
            false,
            true,
            false
    };

    public TrueFalseScreen() {
        frame = new JFrame("True or False Quiz");
        frame.setSize(600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 240, 200));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        trueOption = new JRadioButton("True");
        falseOption = new JRadioButton("False");
        group = new ButtonGroup();
        group.add(trueOption);
        group.add(falseOption);

        trueOption.setBackground(panel.getBackground());
        falseOption.setBackground(panel.getBackground());

        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener((ActionEvent e) -> handleSubmit());

        panel.add(questionLabel);
        panel.add(trueOption);
        panel.add(falseOption);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(submitButton);

        loadQuestion();

        frame.add(panel);
        frame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText("Q" + (currentQuestion + 1) + ": " + questions[currentQuestion]);
            group.clearSelection(); // clear radio buttons
        }
    }

    private void handleSubmit() {
        if (!trueOption.isSelected() && !falseOption.isSelected()) {
            JOptionPane.showMessageDialog(frame, "⚠️ Please select an answer.");
            return;
        }

        boolean selectedAnswer = trueOption.isSelected();

        if (selectedAnswer == answers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion == questions.length) {
            frame.dispose();
            showResult();
        } else {
            loadQuestion();
        }
    }

    private void showResult() {
        String message = "✅ You scored " + score + " out of " + questions.length + ".";
        if (score == 10) {
            message += "\n🎉 Congratulations! You earned a badge! 🏅";
        }
        JOptionPane.showMessageDialog(null, message);
        new QuizTopicScreen(); // go back to topic selection
    }
}
