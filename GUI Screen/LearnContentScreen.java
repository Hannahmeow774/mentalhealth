import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LearnContentScreen {
    JFrame frame;
    private static final String RESOURCES_PATH = "C:\\Users\\OWNER\\Desktop\\Java jdk\\AppHealth\\src\\";

    public LearnContentScreen(String topic, UserProfile user) {
        frame = new JFrame("Content: " + topic);
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(200, 162, 200));

        JLabel titleLabel = new JLabel(topic, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel);

        // Load and add image (beauty section 😍)
        String imagePath = RESOURCES_PATH + getImageFileForTopic(topic);
        File imageFile = new File(imagePath);
        JLabel imageLabel;

        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(300, 180, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(img));
        } else {
            imageLabel = new JLabel("No Image Found 😢");
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        // Load and add content text
        JTextArea textArea = new JTextArea(getContentForTopic(topic));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(20, 10, 10, 10));
        textArea.setBackground(new Color(230, 210, 230));
        textArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        textArea.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(scrollPane);

        // Back button
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            frame.dispose();
            new LearnTopicsScreen(user);
        });

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(backButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private String getContentForTopic(String topic) {
        String filePath = RESOURCES_PATH + getFileNameForTopic(topic);
        File file = new File(filePath);

        if (file.exists()) {
            try {
                FileManager fileManager = new FileManager();
                return fileManager.loadContent(filePath);
            } catch (Exception e) {
                System.out.println("Error loading content: " + e.getMessage());
            }
        }
        return getDefaultContentForTopic(topic);
    }

    private String getFileNameForTopic(String topic) {
        switch (topic) {
            case "Introduction to Mental Health": return "Introduction to Mental Health.txt";
            case "Common Mental Health Disorders": return "Common Mental Health Disorders.txt";
            case "Symptoms of Depression and Anxiety": return "Symptoms of Depression and Anxiety.txt";
            case "Myths and Facts About Mental Health": return "Myths and Facts About Mental Health.txt";
            case "Coping Mechanisms and Self-care": return "Coping Mechanisms and Self-care.txt";
            case "The Role of Therapy and Counseling": return "The Role of Therapy and Counseling.txt";
            case "Mental Health in Youth and Schools": return "Mental Health in Youth and Schools.txt";
            case "Workplace Mental Health Awareness": return "Workplace Mental Health Awareness.txt";
            case "Supporting Someone with Mental Health Issues": return "Supporting Someone with Mental Health Issues.txt";
            case "Support Networks and Community Resources": return "Support Networks and Community Resources.txt";
            default: return "default.txt";
        }
    }

    private String getImageFileForTopic(String topic) {
        switch (topic) {
            case "Introduction to Mental Health": return "Intro to MH.jpg";
            case "Common Mental Health Disorders": return "Common MH.jpg";
            case "Symptoms of Depression and Anxiety": return "Symptoms of A&D.jpg";
            case "Myths and Facts About Mental Health": return "Myth & Fact.jpg";
            case "Coping Mechanisms and Self-care": return "Coping Mechanism.png";
            case "The Role of Therapy and Counseling": return "Role of therapy.jpg";
            case "Mental Health in Youth and Schools": return "MH in youth.png";
            case "Workplace Mental Health Awareness": return "Workplace MH.png";
            case "Supporting Someone with Mental Health Issues": return "Support with MH.png";
            case "Support Networks and Community Resources": return "Support Network.png";
            default: return "fallback.png";
        }
    }

    private String getDefaultContentForTopic(String topic) {
        return "Content for " + topic + " is not available.";
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(153, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}
