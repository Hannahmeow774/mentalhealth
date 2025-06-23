// Updated LearningModule.java to properly implement ILearning
import java.util.ArrayList;
import java.util.List;

public class LearningModule implements ILearning {
    private String topic;
    private List<String> pages;
    private int currentPageIndex;

    public LearningModule() {
        this.topic = "Mental Health Awareness";
        this.pages = new ArrayList<>();
        this.currentPageIndex = 0;
    }

    public LearningModule(String topic) {
        this.topic = topic;
        this.pages = new ArrayList<>();
        this.currentPageIndex = 0;
    }

    @Override
    public void loadContent() {
        pages.clear();
        
        // Load content based on topic
        switch (topic) {
            case "Introduction to Mental Health":
                pages.add("Welcome to Mental Health Awareness!");
                pages.add("Mental health includes our emotional, psychological, and social well-being.");
                pages.add("It affects how we think, feel, and act.");
                break;
            case "Common Mental Health Disorders":
                pages.add("Depression is one of the most common mental health disorders.");
                pages.add("Anxiety disorders affect millions of people worldwide.");
                pages.add("Bipolar disorder involves extreme mood swings.");
                break;
            case "Coping Mechanisms and Self-care":
                pages.add("Regular exercise can improve mental health.");
                pages.add("Mindfulness and meditation are effective coping strategies.");
                pages.add("Maintaining social connections is crucial for well-being.");
                break;
            default:
                pages.add("Welcome to Mental Health Awareness!");
                pages.add("Mindfulness improves mental wellbeing.");
                pages.add("Taking care of your mental health is important.");
        }
    }

    @Override
    public void displayContent() {
        System.out.println("Topic: " + topic);
        System.out.println("================");
        for (int i = 0; i < pages.size(); i++) {
            System.out.println("Page " + (i + 1) + ": " + pages.get(i));
        }
    }

    // Additional methods for navigation
    public String getCurrentPage() {
        if (currentPageIndex < pages.size()) {
            return pages.get(currentPageIndex);
        }
        return null;
    }

    public boolean hasNextPage() {
        return currentPageIndex < pages.size() - 1;
    }

    public boolean hasPreviousPage() {
        return currentPageIndex > 0;
    }

    public void nextPage() {
        if (hasNextPage()) {
            currentPageIndex++;
        }
    }

    public void previousPage() {
        if (hasPreviousPage()) {
            currentPageIndex--;
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTotalPages() {
        return pages.size();
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }
}