import javax.swing.*;
import java.awt.*;

public class StartScreen {
    JFrame frame;
    private UserProfile user;

    public StartScreen(UserProfile user) {
        this.user = user;
        frame = new JFrame("Mental Health Awareness");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(200, 162, 200));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(50));

        try {
            ImageIcon icon = new ImageIcon("C:\\Users\\OWNER\\Desktop\\Java jdk\\AppHealth\\src\\mental_health.png");
            JLabel imageLabel = icon.getIconWidth() > 0 ? new JLabel(icon) : new JLabel("🧠");
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 48));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(imageLabel);
        } catch (Exception e) {
            JLabel fallback = new JLabel("🧠");
            fallback.setFont(new Font("Arial", Font.PLAIN, 48));
            fallback.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(fallback);
        }

        mainPanel.add(Box.createVerticalStrut(20));
        JLabel textLabel = new JLabel("MENTAL HEALTH");
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(textLabel);
        mainPanel.add(Box.createVerticalStrut(30));

        JLabel nameLabel = new JLabel("Enter your username:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameField);
        mainPanel.add(Box.createVerticalStrut(20));

        RoundedButton startButton = new RoundedButton("Start");
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            String username = nameField.getText().trim();
            if (!username.isEmpty()) {
                user.setUsername(username);
                frame.dispose();
                new OptionScreen(user);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a username.");
            }
        });

        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(50));
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

