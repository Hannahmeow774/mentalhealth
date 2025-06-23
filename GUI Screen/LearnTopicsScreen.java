import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LearnTopicsScreen {
    private JFrame frame;
    private UserProfile user;

    public LearnTopicsScreen(UserProfile user) {
        this.user = user;
        frame = new JFrame("Mental Health Awareness - Topics");
        frame.setSize(350, 500); // Standard size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200)); // soft purple

        // Title
        JLabel titleLabel = new JLabel("Choose a Topic to Learn");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center Panel with GridLayout for Topics (3x3 for 9 items)
        JPanel topicsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        topicsPanel.setOpaque(false);
        topicsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<String> topics = Arrays.asList(
            "Introduction to Mental Health",
            "Common Mental Health Disorders",
            "Symptoms of Depression and Anxiety",
            "Myths and Facts About Mental Health",
            "Coping Mechanisms and Self-care",
            "The Role of Therapy and Counseling",
            "Mental Health in Youth and Schools",
            "Workplace Mental Health Awareness",
            "Supporting Someone with Mental Health Issues",
            "Support Networks and Community Resources"
        );

        for (String topic : topics) {
            RoundedButton topicButton = new RoundedButton("<html><center>" + shortenTopic(topic) + "</center></html>");
            topicButton.setPreferredSize(new Dimension(140, 60));
            String actualTopic = topic; // full name
            topicButton.addActionListener(e -> {
                frame.dispose();
                new LearnContentScreen(actualTopic, user); // passing UserProfile!
            });
            topicsPanel.add(topicButton);
        }

        // Back Button at Bottom Right
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        RoundedButton backButton = new RoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            frame.dispose();
            new OptionScreen(user);
        });
        bottomPanel.add(backButton);

        mainPanel.add(topicsPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private String shortenTopic(String topic) {
        if (topic.equals("Supporting Someone with Mental Health Issues")) return "Support for Others";
        if (topic.equals("Support Networks and Community Resources")) return "Support Networks";
        if (topic.equals("Mental Health in Youth and Schools")) return "Youth Mental Health";
        if (topic.equals("Workplace Mental Health Awareness")) return "Workplace MH";
        return topic;
    }
}
