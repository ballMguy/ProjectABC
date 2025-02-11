package projectabc;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SaveManager {
    public static void saveUserData(String username, List<ToDoItem> todos) {
        try (FileWriter writer = new FileWriter("data/" + username + "_todos.json")) {
            new Gson().toJson(todos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ToDoItem> loadUserData(String username) {
        File file = new File("data/" + username + "_todos.json");
        if (!file.exists()) {
            return new ArrayList<>(); // 🔥 ถ้าไฟล์ไม่มี ให้คืนค่าเป็นลิสต์ว่าง
        }
        try (FileReader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, new TypeToken<List<ToDoItem>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // 🔥 ถ้าอ่านไฟล์ไม่ได้ ให้คืนค่าลิสต์ว่าง
        }
    }
}
