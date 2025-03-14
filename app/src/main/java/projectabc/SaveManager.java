package projectabc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * คลาสสำหรับจัดการการบันทึกและโหลดข้อมูล To-Do List ของผู้ใช้
 */
public class SaveManager {

    /**
     * บันทึกข้อมูล To-Do List ของผู้ใช้ลงในไฟล์ JSON พร้อมเข้ารหัสข้อมูล
     * 
     * @param username ชื่อผู้ใช้
     * @param todos รายการงานที่ต้องทำของผู้ใช้
     */
    public static void saveUserData(String username, List<ToDoItem> todos) {
        try (FileWriter writer = new FileWriter(username + "_todos.json")) {
            String json = new Gson().toJson(todos);
            String encryptedJson = EncryptionUtil.encrypt(json);  // ✅ เข้ารหัสก่อนบันทึก
            writer.write(encryptedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * โหลดข้อมูล To-Do List ของผู้ใช้จากไฟล์ JSON พร้อมถอดรหัสข้อมูล
     * 
     * @param username ชื่อผู้ใช้
     * @return รายการงานที่ต้องทำของผู้ใช้
     */
    public static List<ToDoItem> loadUserData(String username) {
        File file = new File(username + "_todos.json");
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String encryptedData = reader.readLine();
            String json = EncryptionUtil.decrypt(encryptedData);  // ✅ ถอดรหัสก่อนโหลด
            return new Gson().fromJson(json, new TypeToken<List<ToDoItem>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
