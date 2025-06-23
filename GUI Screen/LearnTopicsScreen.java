// made by Hannah

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LearnTopicsScreen {
    JFrame frame;

    public LearnTopicsScreen() {
        frame = new JFrame("Mental Health Awareness - Topics");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        // Panel with GridLayout to avoid scrolling
        JPanel topicsPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns
        topicsPanel.setOpaque(false);
        topicsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

        // Load topics from the file
        List<String> topics = FileManager.loadTopics("Resources/Resources topics.txt");

        for (String topic : topics) {
            String displayTopic = topic;

            // Shorten only for display
            if (topic.equals("Supporting Someone with Mental Health Issues")) {
                displayTopic = "Support for Others";
            }

            RoundedButton topicButton = new RoundedButton("<html><center>" + displayTopic + "</center></html>");
            topicButton.setPreferredSize(new Dimension(150, 60));

            String actualTopic = topic; // Pass the full name to LearnContentScreen

            topicButton.addActionListener(e -> {
                frame.dispose();
                new LearnContentScreen(actualTopic);
            });

            topicsPanel.add(topicButton);
        }

        // Bottom panel with "Back" button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);

        RoundedButton backButton = new RoundedButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            frame.dispose();
            new OptionScreen();
        });

        bottomPanel.add(backButton);

        mainPanel.add(topicsPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

// Custom Rounded Button Class
class RoundedButton extends JButton {
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setBackground(new Color(153, 102, 204)); // soft purple
        setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }
}
