// IStorage.java
import java.util.List;

public interface IStorage {
    void saveData(UserProfile user);
    List<UserProfile> loadData();
}
