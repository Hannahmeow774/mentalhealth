import javax.swing.*;
import java.awt.*;

public class OptionScreen {
    private JFrame frame;
    private UserProfile user;

    public OptionScreen(UserProfile user) {
        this.user = user;
        frame = new JFrame("Options");
        frame.setSize(350, 500); // Standardized as per your request
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200)); // soft purple background

        // Center Panel with BoxLayout (for vertical stacking)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(200, 162, 200));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select an Option");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Learn Button
        RoundedButton learnButton = new RoundedButton("Learn");
        learnButton.setPreferredSize(new Dimension(200, 50)); // Bigger button
        learnButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        learnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        learnButton.addActionListener(e -> {
            frame.dispose();
            new LearnTopicsScreen(user);
        });

        // Quiz Button
        RoundedButton quizButton = new RoundedButton("Quiz");
        quizButton.setPreferredSize(new Dimension(200, 50)); // Bigger button
        quizButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.addActionListener(e -> {
            frame.dispose();
            new QuizTopicScreen(user);
        });

        // Add components to center panel
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(learnButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(quizButton);

        // Bottom panel for Back button (aligned to right)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(200, 162, 200));

        RoundedButton backButton = new RoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            frame.dispose();
            new StartScreen(); // Go back to StartScreen (no user needed there)
        });
        bottomPanel.add(backButton);

        // Add everything to the main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
