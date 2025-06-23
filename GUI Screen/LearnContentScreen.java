// made by Hannah

import javax.swing.*;
import java.awt.*;

public class LearnContentScreen {
    JFrame frame;

    public LearnContentScreen(String topic) {
        frame = new JFrame("Mental Health Awareness - Content");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBackground(new Color(230, 210, 230));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        textArea.setForeground(Color.BLACK);
        textArea.setOpaque(true);

        // Load content from file using FileManager
        FileManager fileManager = new FileManager();
        String fileName = getFileNameForTopic(topic);
        String content = fileManager.loadContent("Resources/" + fileName);
        textArea.setText(content);

        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setBorder(null);
        textScrollPane.setOpaque(false);
        textScrollPane.getViewport().setOpaque(false);

        // Image Section
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setOpaque(false);

        JLabel descriptionLabel = new JLabel("🖼️ Image: " + topic);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String imagePath = getImagePathForTopic(topic);
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image resizedImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imagePanel.add(Box.createVerticalStrut(10));
        imagePanel.add(descriptionLabel);
        imagePanel.add(Box.createVerticalStrut(10));
        imagePanel.add(imageLabel);
        imagePanel.add(Box.createVerticalStrut(10));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.add(textScrollPane);
        centerPanel.add(imagePanel);

        // Back button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new LearnTopicsScreen();
        });

        bottomPanel.add(backButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Map topic to the correct file name in Resources folder
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

    // Map topic to the correct image path
    private String getImagePathForTopic(String topic) {
        switch (topic) {
            case "Introduction to Mental Health": return "Resources/Intro to MH.jpg";
            case "Common Mental Health Disorders": return "Resources/Common MH.jpg";
            case "Symptoms of Depression and Anxiety": return "Resources/Symptoms of A&D.jpg";
            case "Myths and Facts About Mental Health": return "Resources/Myth & Fact.jpg";
            case "Coping Mechanisms and Self-care": return "Resources/Coping Mechanism.png";
            case "The Role of Therapy and Counseling": return "Resources/Role of therapy.jpg";
            case "Mental Health in Youth and Schools": return "Resources/MH in youth.png";
            case "Workplace Mental Health Awareness": return "Resources/Workplace MH.png";
            case "Supporting Someone with Mental Health Issues": return "Resources/Support with MH.png";
            case "Support Networks and Community Resources": return "Resources/Support Network.png";
            default: return "Resources/fallback.png";
        }
    }
}
