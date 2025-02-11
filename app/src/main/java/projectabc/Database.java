package projectabc;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Database {
    private static final String FILE_PATH = "users_encrypted.json"; //เก็บข้อมูลแบบเข้ารหัส
    private static final String SECRET_KEY = "0123456789abcdef"; // คีย์เข้ารหัส AES (ต้องมี 16 ตัวอักษร)
    private static HashMap<String, String> users = new HashMap<>();

    static {
        loadUsers();
    }
    

    // โหลดข้อมูลผู้ใช้จากไฟล์ (Decrypt)
    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String encryptedData = reader.readLine();
            if (encryptedData != null) {
                String decryptedJson = decrypt(encryptedData);
                users = new Gson().fromJson(decryptedJson, new TypeToken<HashMap<String, String>>() {}.getType());
            }
        } catch (FileNotFoundException e) {
            users = new HashMap<>(); // ถ้าไฟล์ไม่มี ให้สร้างใหม่
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // บันทึกข้อมูลลงไฟล์ (Encrypt)
    public static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String json = new Gson().toJson(users);
            String encryptedData = encrypt(json);
            writer.write(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // เพิ่มผู้ใช้ใหม่
    public static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // มีชื่อผู้ใช้อยู่แล้ว
        }
        users.put(username, encrypt(password));
        saveUsers();
        return true;
    }

    // ตรวจสอบการ Login
    public static boolean validateUser(String username, String password) {
        return users.containsKey(username) && decrypt(users.get(username)).equals(password);
    }

    // โหลดรายการ To-Do ของผู้ใช้
    public static List<ToDoItem> loadUserTodos(String username) {
        return SaveManager.loadUserData(username);
    }

    // บันทึก To-Do List ของผู้ใช้
    public static void saveUserTodos(String username, List<ToDoItem> todoList) {
        SaveManager.saveUserData(username, todoList);
        
    }

    // ฟังก์ชันเข้ารหัส AES
    private static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting", e);
        }
    }

    // ฟังก์ชันถอดรหัส AES
    private static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting", e);
        }
    }
}
