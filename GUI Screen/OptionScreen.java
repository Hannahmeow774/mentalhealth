import javax.swing.*;
import java.awt.*;

public class OptionScreen {
    private JFrame frame;
    private UserProfile user;

    public OptionScreen(UserProfile user) {
        this.user = user;
        frame = new JFrame("Options");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(200, 162, 200));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select an Option");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton learnButton = new RoundedButton("Learn");
        learnButton.setPreferredSize(new Dimension(300, 60));
        learnButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // 💜 BIGGER FONT!
        learnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        learnButton.addActionListener(e -> {
            frame.dispose();
            new LearnTopicsScreen(user);
        });

        RoundedButton quizButton = new RoundedButton("Quiz");
        quizButton.setPreferredSize(new Dimension(300, 60));
        quizButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // 💜 BIGGER FONT!
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.addActionListener(e -> {
            frame.dispose();
            new QuizTopicScreen(user);
        });

        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(learnButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(quizButton);

        RoundedButton backButton = new RoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Optional: Back button font
        backButton.addActionListener(e -> {
            frame.dispose();
            new StartScreen(user);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(200, 162, 200));
        bottomPanel.add(backButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
