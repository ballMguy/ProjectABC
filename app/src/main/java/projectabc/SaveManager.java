package projectabc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        try (FileReader reader = new FileReader("data/" + username + "_todos.json")) {
            return new Gson().fromJson(reader, new TypeToken<List<ToDoItem>>(){}.getType());
        } catch (IOException e) {
            return new ArrayList<>(); // ถ้าไม่มีไฟล์ให้คืนค่าเป็นลิสต์ว่าง
        }
    }
}
