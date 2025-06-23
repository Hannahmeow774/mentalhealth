import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements IStorage {
    private String fileName;

    public FileManager() {
        this.fileName = "userData.txt";
    }

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveData(UserProfile user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(user.getProfile());
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<UserProfile> loadData() {
        List<UserProfile> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse user data from saved format
                if (line.startsWith("User: ")) {
                    String[] parts = line.split(", ");
                    if (parts.length >= 2) {
                        String username = parts[0].substring(6); // Remove "User: "
                        String scoreStr = parts[1].substring(7); // Remove "Score: "

                        try {
                            int score = Integer.parseInt(scoreStr);
                            UserProfile user = new UserProfile(username);
                            user.setScore(score);
                            users.add(user);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing score: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found, starting with empty user list.");
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }

        if (users.isEmpty()) {
            users.add(new UserProfile("SampleUser"));
        }

        return users;
    }

    // ✅ This method is now OUTSIDE loadData()
    public String loadContent(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file content: " + e.getMessage());
            e.printStackTrace();
        }

        return content.toString();
    }
}
