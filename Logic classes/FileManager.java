// made by Hannah

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements IStorage {
    private String fileName = "userData.txt";

    public void saveData(UserProfile user) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(user.getProfile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<UserProfile> loadData() {
        List<UserProfile> users = new ArrayList<>();
        users.add(new UserProfile("SampleUser"));
        return users;
    }

    // ✅ For LearnTopicsScreen
    public static List<String> loadTopics(String filePath) {
        List<String> topics = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    topics.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            topics.add("Failed to load topics.");
        }

        return topics;
    }

    // ✅ For LearnContentScreen (THIS FIXES YOUR ERROR)
    public String loadContent(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to load content.";
        }

        return content.toString();
    }
}
