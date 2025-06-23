import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LearnTopicsScreen {
    JFrame frame;
    private UserProfile user;

    public LearnTopicsScreen(UserProfile user) {
        this.user = user;
        frame = new JFrame("Learn Topics");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        JLabel titleLabel = new JLabel("Choose a Topic to Learn");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel topicsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        topicsPanel.setOpaque(false);
        topicsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<String> topics = Arrays.asList(
            "Introduction to Mental Health", "Common Mental Health Disorders",
            "Symptoms of Depression and Anxiety", "Myths and Facts About Mental Health",
            "Coping Mechanisms and Self-care", "The Role of Therapy and Counseling",
            "Mental Health in Youth and Schools", "Workplace Mental Health Awareness",
            "Supporting Someone with Mental Health Issues", "Support Networks and Community Resources"
        );

        for (String topic : topics) {
            RoundedButton topicButton = new RoundedButton("<html><center>" + topic + "</center></html>");
            topicButton.setPreferredSize(new Dimension(200, 80));
            topicButton.addActionListener(e -> {
                frame.dispose();
                new LearnContentScreen(topic, user);
            });
            topicsPanel.add(topicButton);
        }

        RoundedButton backButton = new RoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            frame.dispose();
            new OptionScreen(user);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(backButton);

        mainPanel.add(topicsPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
