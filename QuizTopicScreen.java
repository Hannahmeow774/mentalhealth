// made by Scarlett

import javax.swing.*;
import java.awt.*;

public class QuizTopicScreen{
    JFrame frame;

    public QuizTopicScreen() {
        frame = new JFrame("Mental Health Awareness - Topics");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center screen

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(200, 162, 200)); // lavender color

        JPanel topicsPanel = new JPanel();
        topicsPanel.setLayout(new BoxLayout(topicsPanel, BoxLayout.Y_AXIS));
        topicsPanel.setOpaque(false); // transparent so mainPanel background shows

        // List of quiz types
        String[] topics = {
                "Multiple Choice Question",
                "True or False",
                "Fill in the blank"
        };

        // Create a button for each topic
        for (String topic : topics) {
            JButton topicButton = new JButton(topic);
            topicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            topicButton.setMaximumSize(new Dimension(300, 40)); // same width for all buttons
            topicButton.addActionListener(e -> {
                frame.dispose(); // close this window

                // Open the respective quiz screen based on topic
                switch (topic) {
                    case "Multiple Choice Question":
                        new MultipleChoiceScreen();
                        break;
                    case "True or False":
                        new TrueFalseScreen();
                        break;
                    case "Fill in the blank":
                        new FillInBlankScreen();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Coming soon!");
                }
            });

            topicsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // vertical spacing
            topicsPanel.add(topicButton);
        }

        // Add scroll pane in case more topics are added later
        JScrollPane scrollPane = new JScrollPane(topicsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        // Bottom panel with "Back" button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose(); // close this screen
            new OptionScreen(); // return to previous menu (you must have OptionScreen.java)
        });

        bottomPanel.add(backButton);

        // Add everything to main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
