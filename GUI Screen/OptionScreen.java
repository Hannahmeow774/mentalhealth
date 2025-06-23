// made by Hannah

import javax.swing.*;
import java.awt.*;

public class OptionScreen {
    JFrame frame;

    public OptionScreen() {
        frame = new JFrame("Mental Health Awareness - Options");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        // Center panel for label and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(200, 162, 200));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select an Option");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton learnButton = new JButton("Learn");
        learnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        learnButton.setMaximumSize(new Dimension(200, 40));
        learnButton.addActionListener(e -> {
            frame.dispose();
            new LearnTopicsScreen();
        });

        JButton quizButton = new JButton("Quiz");
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.setMaximumSize(new Dimension(200, 40));
        quizButton.addActionListener(e -> {
            frame.dispose();
            new QuizTopicScreen();
        });

        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(learnButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(quizButton);

        // Bottom panel for back button (FlowLayout right)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(200, 162, 200));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new StartScreen();
        });
        bottomPanel.add(backButton);

        // Add panels to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}