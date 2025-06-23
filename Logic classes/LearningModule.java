import java.util.ArrayList;
import java.util.List;

public class LearningModule implements ILearning {
    private String topic;
    private List<String> pages;

    public LearningModule() {
        topic = "Mental Health Awareness";
        pages = new ArrayList<>();
    }

    public void loadContent() {
        pages.add("Welcome to Mental Health Awareness!");
        pages.add("Mindfulness improves mental wellbeing.");
    }

    public void displayContent() {
        for (String page : pages) {
            System.out.println(page);
        }
    }
}

