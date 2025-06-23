import javax.swing.*;
import java.awt.*;

public class QuizTopicScreen {
    private JFrame frame;
    private UserProfile user;

    public QuizTopicScreen(UserProfile user) {
        this.user = user;
        frame = new JFrame("Select Quiz Type");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        // Center panel for buttons and label
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JLabel label = new JLabel("Choose Quiz Mode");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // MCQ Normal (Navy Blue)
        RoundedButton mcqNormal = new RoundedButton("MCQ [Normal Mode]", new Color(0, 0, 128)); // Navy
        mcqNormal.setPreferredSize(new Dimension(250, 50));
        mcqNormal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mcqNormal.setAlignmentX(Component.CENTER_ALIGNMENT);
        mcqNormal.addActionListener(e -> {
            frame.dispose();
            new MCQQuizScreen(user, false);
        });

        // MCQ Timer (Navy Blue)
        RoundedButton mcqTimer = new RoundedButton("MCQ [Timer Mode]", new Color(0, 0, 128)); // Navy
        mcqTimer.setPreferredSize(new Dimension(250, 50));
        mcqTimer.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mcqTimer.setAlignmentX(Component.CENTER_ALIGNMENT);
        mcqTimer.addActionListener(e -> {
            frame.dispose();
            new MCQQuizScreen(user, true);
        });

        // True/False Normal (Watermelon Pink)
        RoundedButton tfNormal = new RoundedButton("True/False [Normal Mode]", new Color(252, 108, 133)); // Watermelon Pink
        tfNormal.setPreferredSize(new Dimension(250, 50));
        tfNormal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tfNormal.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfNormal.addActionListener(e -> {
            frame.dispose();
            new TFQuizScreen(user, false);
        });

        // True/False Timer (Watermelon Pink)
        RoundedButton tfTimer = new RoundedButton("True/False [Timer Mode]", new Color(252, 108, 133)); // Watermelon Pink
        tfTimer.setPreferredSize(new Dimension(250, 50));
        tfTimer.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tfTimer.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfTimer.addActionListener(e -> {
            frame.dispose();
            new TFQuizScreen(user, true);
        });

        // Back Button (Bottom Right)
        RoundedButton backButton = new RoundedButton("Back", new Color(153, 102, 204)); // Soft Purple
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            frame.dispose();
            new OptionScreen(user);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(backButton);

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(mcqNormal);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(mcqTimer);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(tfNormal);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(tfTimer);
        centerPanel.add(Box.createVerticalStrut(20));

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
