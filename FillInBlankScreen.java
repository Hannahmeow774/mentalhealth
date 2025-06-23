// made by Dayang

import javax.swing.*;
import java.awt.*;

public class FillInBlankScreen {
    JFrame frame;

    public FillInBlankScreen() {
        frame = new JFrame("Fill in the Blank Quiz");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(210, 255, 210)); // light green

        JLabel questionLabel = new JLabel("Fill in the blank: _____ health is just as important as physical health.");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JTextField answerField = new JTextField();
        answerField.setMaximumSize(new Dimension(300, 30));
        answerField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String userAnswer = answerField.getText().trim().toLowerCase();
            if (userAnswer.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "⚠️ Please enter an answer.");
            } else if (userAnswer.equals("mental")) {
                JOptionPane.showMessageDialog(frame, "✅ Correct!");
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Incorrect. The correct answer is: mental.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            frame.dispose();
            new QuizTopicScreen();
        });

        panel.add(questionLabel);
        panel.add(answerField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(submitButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
