import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LearnContentScreen {
    JFrame frame;
    private static final String RESOURCES_PATH = "C:\\Users\\OWNER\\Desktop\\Java jdk\\AppHealth\\src\\"; 
    private UserProfile user;

    public LearnContentScreen(String topic, UserProfile user) {
        this.user = user;
        frame = new JFrame("Content: " + topic);
        frame.setSize(350, 500); // STANDARD SIZE
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200)); // Soft purple

        // Title Label (Top)
        JLabel titleLabel = new JLabel(topic, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Text Area (Center)
        JTextArea textArea = new JTextArea(getContentForTopic(topic));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(5, 5, 5, 5));
        textArea.setBackground(new Color(230, 210, 230));
        textArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setPreferredSize(new Dimension(320, 180)); // fits well in frame

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Image Section (Below Text)
        String imagePath = getImagePathForTopic(topic);
        JLabel imageLabel = createImageLabel(imagePath);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);
        imagePanel.add(imageLabel);

        mainPanel.add(imagePanel, BorderLayout.SOUTH);

        // Back Button (Bottom)
        RoundedButton backButton = new RoundedButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(e -> {
            frame.dispose();
            new LearnTopicsScreen(user);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(200, 162, 200));
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private String getContentForTopic(String topic) {
        String filePath = RESOURCES_PATH + "\\" + getFileNameForTopic(topic);
        File file = new File(filePath);
        System.out.println("Loading content from: " + filePath);

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

    private String getDefaultContentForTopic(String topic) {
        return "Content for " + topic + " is not available.";
    }

    private JLabel createImageLabel(String imagePath) {
        try {
            File file = new File(imagePath);
            System.out.println("Trying to load image: " + imagePath);
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image scaled = icon.getImage().getScaledInstance(300, 120, Image.SCALE_SMOOTH); // Scales perfectly into 350x500 frame
                return new JLabel(new ImageIcon(scaled));
            } else {
                System.out.println("Image not found at: " + imagePath);
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        return new JLabel("📚"); // fallback emoji
    }

    private String getImagePathForTopic(String topic) {
        switch (topic) {
            case "Introduction to Mental Health": return RESOURCES_PATH + "\\Intro to MH.jpg";
            case "Common Mental Health Disorders": return RESOURCES_PATH + "\\Common MH.jpg";
            case "Symptoms of Depression and Anxiety": return RESOURCES_PATH + "\\Symptoms of A&D.jpg";
            case "Myths and Facts About Mental Health": return RESOURCES_PATH + "\\Myth & Fact.jpg";
            case "Coping Mechanisms and Self-care": return RESOURCES_PATH + "\\Coping Mechanism.png";
            case "The Role of Therapy and Counseling": return RESOURCES_PATH + "\\Role of therapy.jpg";
            case "Mental Health in Youth and Schools": return RESOURCES_PATH + "\\MH in youth.png";
            case "Workplace Mental Health Awareness": return RESOURCES_PATH + "\\Workplace MH.png";
            case "Supporting Someone with Mental Health Issues": return RESOURCES_PATH + "\\Support with MH.png";
            case "Support Networks and Community Resources": return RESOURCES_PATH + "\\Support Network.png";
            default: return RESOURCES_PATH + "\\fallback.png";
        }
    }
}
