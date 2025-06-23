// Made by Hannah 💜

import java.awt.*;
import javax.swing.*;

public class StartScreen {
    JFrame frame;

    public StartScreen() {
        frame = new JFrame("Mental Health Awareness");
        frame.setSize(350, 500); // standardized size as you wanted
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(200, 162, 200)); // soft purple
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalStrut(30));

        // Icon or emoji fallback
        try {
            ImageIcon icon = new ImageIcon("C:\\Users\\OWNER\\Desktop\\Java jdk\\AppHealth\\src\\mental_health.png");
            if (icon.getIconWidth() > 0) {
                JLabel imageLabel = new JLabel(icon);
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainPanel.add(imageLabel);
            } else {
                JLabel imageLabel = new JLabel("🧠");
                imageLabel.setFont(new Font("Arial", Font.PLAIN, 48));
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainPanel.add(imageLabel);
            }
        } catch (Exception e) {
            JLabel imageLabel = new JLabel("🧠");
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 48));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(imageLabel);
        }

        mainPanel.add(Box.createVerticalStrut(15));

        JLabel textLabel = new JLabel("MENTAL HEALTH");
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(textLabel);

        mainPanel.add(Box.createVerticalStrut(20));

        JLabel nameLabel = new JLabel("Enter your username:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameField);

        mainPanel.add(Box.createVerticalStrut(20));

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(120, 40)); // bigger as you wanted
        startButton.setBackground(new Color(0, 123, 255)); // blue
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            String username = nameField.getText().trim();
            if (!username.isEmpty()) {
                frame.dispose();
                new OptionScreen(new UserProfile(username)); // pass the username to OptionScreen
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a username.");
            }
        });

        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(30));

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

