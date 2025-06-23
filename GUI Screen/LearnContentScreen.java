// made by Hannah

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LearnContentScreen {
    JFrame frame;
    private static final String RESOURCES_PATH = "C:\\Users\\OWNER\\Desktop\\Java jdk\\AppHealth\\Resources\\";

    public LearnContentScreen(String topic) {
        frame = new JFrame("Mental Health Awareness - " + topic);
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200));

        // Title
        JLabel titleLabel = new JLabel(topic);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBackground(new Color(230, 210, 230));
        textArea.setMargin(new Insets(15, 15, 15, 15));
        textArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        textArea.setForeground(Color.BLACK);
        textArea.setOpaque(true);

        // Load content from FileManager or use default content
        String content = getContentForTopic(topic);
        textArea.setText(content);

        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textScrollPane.setOpaque(false);
        textScrollPane.getViewport().setOpaque(false);

        // Image Section
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setOpaque(false);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel descriptionLabel = new JLabel("📚 " + topic);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 12));

        // Try to load image, use emoji fallback if not found
        String imagePath = getImagePathForTopic(topic);
        JLabel imageLabel = createImageLabel(imagePath, getEmojiForTopic(topic));
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
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(153, 102, 204));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
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

    private JLabel createImageLabel(String imagePath, String fallbackEmoji) {
        try {
            // Check if the file exists before trying to load it
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ImageIcon originalIcon = new ImageIcon(imagePath);
                if (originalIcon.getIconWidth() > 0) {
                    Image resizedImage = originalIcon.getImage().getScaledInstance(300,180, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                    return new JLabel(resizedIcon);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath + " - " + e.getMessage());
        }
        
        // Try fallback.png if main image doesn't exist
        try {
            String fallbackPath = RESOURCES_PATH + "fallback.png";
            File fallbackFile = new File(fallbackPath);
            if (fallbackFile.exists()) {
                ImageIcon fallbackIcon = new ImageIcon(fallbackPath);
                if (fallbackIcon.getIconWidth() > 0) {
                    Image resizedImage = fallbackIcon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                    return new JLabel(resizedIcon);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading fallback image - " + e.getMessage());
        }
        
        // Final fallback to emoji if both image and fallback.png don't exist
        JLabel emojiLabel = new JLabel(fallbackEmoji);
        emojiLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return emojiLabel;
    }

    private String getEmojiForTopic(String topic) {
        switch (topic) {
            case "Introduction to Mental Health": return "🧠";
            case "Common Mental Health Disorders": return "🏥";
            case "Symptoms of Depression and Anxiety": return "😔";
            case "Myths and Facts About Mental Health": return "💭";
            case "Coping Mechanisms and Self-care": return "🌱";
            case "The Role of Therapy and Counseling": return "👥";
            case "Mental Health in Youth and Schools": return "🎓";
            case "Workplace Mental Health Awareness": return "💼";
            case "Supporting Someone with Mental Health Issues": return "🤝";
            case "Support Networks and Community Resources": return "🌍";
            default: return "💚";
        }
    }

    private String getContentForTopic(String topic) {
        // Try to load from file first
        try {
            FileManager fileManager = new FileManager();
            String fileName = getFileNameForTopic(topic);
            String fullPath = RESOURCES_PATH + fileName;
            
            // Check if file exists before trying to load
            File contentFile = new File(fullPath);
            if (contentFile.exists()) {
                String content = fileManager.loadContent(fullPath);
                if (content != null && !content.trim().isEmpty()) {
                    return content;
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading content for topic: " + topic + " - " + e.getMessage());
        }

        // Default content if file doesn't exist
        return getDefaultContentForTopic(topic);
    }

    private String getDefaultContentForTopic(String topic) {
        switch (topic) {
            case "Introduction to Mental Health":
                return "Mental health refers to our emotional, psychological, and social well-being. It affects how we think, feel, and act as we cope with life.\n\n" +
                       "Good mental health is essential for:\n" +
                       "• Overall well-being and quality of life\n" +
                       "• Healthy relationships with others\n" +
                       "• Productivity and success in work or school\n" +
                       "• The ability to cope with stress and challenges\n\n" +
                       "Mental health is just as important as physical health and should be taken seriously.";

            case "Common Mental Health Disorders":
                return "Common mental health disorders include:\n\n" +
                       "• Depression - Persistent feelings of sadness and loss of interest\n" +
                       "• Anxiety Disorders - Excessive worry and fear\n" +
                       "• Bipolar Disorder - Extreme mood swings\n" +
                       "• PTSD - Trauma-related stress disorder\n" +
                       "• OCD - Obsessive-compulsive behaviors\n" +
                       "• ADHD - Attention and hyperactivity issues\n\n" +
                       "These conditions are treatable with proper care and support.";

            case "Symptoms of Depression and Anxiety":
                return "Depression Symptoms:\n" +
                       "• Persistent sadness or empty mood\n" +
                       "• Loss of interest in activities\n" +
                       "• Fatigue and decreased energy\n" +
                       "• Changes in sleep patterns\n" +
                       "• Difficulty concentrating\n\n" +
                       "Anxiety Symptoms:\n" +
                       "• Excessive worry or fear\n" +
                       "• Restlessness or feeling on edge\n" +
                       "• Rapid heartbeat\n" +
                       "• Sweating or trembling\n" +
                       "• Difficulty sleeping\n\n" +
                       "If you experience these symptoms persistently, consider seeking professional help.";

            case "Myths and Facts About Mental Health":
                return "Common Myths vs Facts:\n\n" +
                       "MYTH: Mental health problems are rare\n" +
                       "FACT: 1 in 4 people experience mental health issues\n\n" +
                       "MYTH: People with mental illness are violent\n" +
                       "FACT: They are more likely to be victims than perpetrators\n\n" +
                       "MYTH: Mental health problems are a sign of weakness\n" +
                       "FACT: They are medical conditions that can affect anyone\n\n" +
                       "MYTH: Therapy and medication don't help\n" +
                       "FACT: Treatment is effective for most people\n\n" +
                       "Understanding these facts helps reduce stigma.";

            case "Coping Mechanisms and Self-care":
                return "Healthy Coping Strategies:\n\n" +
                       "• Regular exercise and physical activity\n" +
                       "• Maintaining a balanced diet\n" +
                       "• Getting adequate sleep (7-9 hours)\n" +
                       "• Practicing mindfulness and meditation\n" +
                       "• Connecting with supportive friends and family\n" +
                       "• Engaging in hobbies and enjoyable activities\n" +
                       "• Setting realistic goals and boundaries\n" +
                       "• Journaling to express thoughts and feelings\n\n" +
                       "Self-care is not selfish - it's essential for mental well-being.";

            case "The Role of Therapy and Counseling":
                return "Therapy and counseling provide:\n\n" +
                       "• A safe, confidential space to talk\n" +
                       "• Professional guidance and support\n" +
                       "• Tools and strategies for managing symptoms\n" +
                       "• Help in understanding thoughts and behaviors\n" +
                       "• Assistance in developing coping skills\n\n" +
                       "Types of therapy include:\n" +
                       "• Cognitive Behavioral Therapy (CBT)\n" +
                       "• Dialectical Behavior Therapy (DBT)\n" +
                       "• Psychodynamic therapy\n" +
                       "• Group therapy\n\n" +
                       "Finding the right therapist and approach is important for success.";

            case "Mental Health in Youth and Schools":
                return "Youth Mental Health:\n\n" +
                       "Challenges facing young people:\n" +
                       "• Academic pressure and stress\n" +
                       "• Social media and peer pressure\n" +
                       "• Identity development and self-esteem issues\n" +
                       "• Bullying and social exclusion\n" +
                       "• Family conflicts and changes\n\n" +
                       "Schools can help by:\n" +
                       "• Providing counseling services\n" +
                       "• Creating supportive environments\n" +
                       "• Teaching emotional regulation skills\n" +
                       "• Reducing stigma through education\n" +
                       "• Training staff to recognize warning signs\n\n" +
                       "Early intervention is key to supporting youth mental health.";

            case "Workplace Mental Health Awareness":
                return "Workplace Mental Health:\n\n" +
                       "Common workplace stressors:\n" +
                       "• Heavy workloads and deadlines\n" +
                       "• Lack of work-life balance\n" +
                       "• Poor communication and conflict\n" +
                       "• Job insecurity and change\n" +
                       "• Lack of support from management\n\n" +
                       "Employers can support mental health by:\n" +
                       "• Providing employee assistance programs\n" +
                       "• Creating flexible work arrangements\n" +
                       "• Promoting open communication\n" +
                       "• Training managers to recognize signs\n" +
                       "• Reducing stigma and discrimination\n\n" +
                       "A mentally healthy workplace benefits everyone.";

            case "Supporting Someone with Mental Health Issues":
                return "How to Support Others:\n\n" +
                       "• Listen without judgment\n" +
                       "• Offer emotional support and encouragement\n" +
                       "• Help them find professional resources\n" +
                       "• Be patient and understanding\n" +
                       "• Don't try to 'fix' them or give advice\n" +
                       "• Maintain regular contact and check-ins\n" +
                       "• Respect their boundaries and choices\n" +
                       "• Take care of your own mental health too\n\n" +
                       "What NOT to say:\n" +
                       "• 'Just think positive'\n" +
                       "• 'Others have it worse'\n" +
                       "• 'You'll get over it'\n\n" +
                       "Your support can make a significant difference.";

            case "Support Networks and Community Resources":
                return "Building Support Networks:\n\n" +
                       "Types of support:\n" +
                       "• Family and friends\n" +
                       "• Mental health professionals\n" +
                       "• Support groups and peer networks\n" +
                       "• Community organizations\n" +
                       "• Online communities and resources\n\n" +
                       "Community resources may include:\n" +
                       "• Community mental health centers\n" +
                       "• Crisis hotlines and helplines\n" +
                       "• Peer support programs\n" +
                       "• Religious or spiritual communities\n" +
                       "• Recreational and volunteer activities\n\n" +
                       "Remember: You don't have to face mental health challenges alone.";

            default:
                return "This topic contains important information about mental health. Please check back later for detailed content.";
        }
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
            case "Introduction to Mental Health": return RESOURCES_PATH + "Intro to MH.jpg";
            case "Common Mental Health Disorders": return RESOURCES_PATH + "Common MH.jpg";
            case "Symptoms of Depression and Anxiety": return RESOURCES_PATH + "Symptoms of A&D.jpg";
            case "Myths and Facts About Mental Health": return RESOURCES_PATH + "Myth & Fact.jpg";
            case "Coping Mechanisms and Self-care": return RESOURCES_PATH + "Coping Mechanism.png";
            case "The Role of Therapy and Counseling": return RESOURCES_PATH + "Role of therapy.jpg";
            case "Mental Health in Youth and Schools": return RESOURCES_PATH + "MH in youth.png";
            case "Workplace Mental Health Awareness": return RESOURCES_PATH + "Workplace MH.png";
            case "Supporting Someone with Mental Health Issues": return RESOURCES_PATH + "Support with MH.png";
            case "Support Networks and Community Resources": return RESOURCES_PATH + "Support Network.png";
            default: return RESOURCES_PATH + "fallback.png";
        }
    }
}