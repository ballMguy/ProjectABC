package projectabc;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * คลาส Database ใช้สำหรับจัดการข้อมูลผู้ใช้ รวมถึงการเข้ารหัสและถอดรหัสข้อมูล
 */
public class Database {
    private static final String FILE_PATH = "users_encrypted.json"; //เก็บข้อมูลแบบเข้ารหัส
    private static final String SECRET_KEY = "0123456789abcdef"; // คีย์เข้ารหัส AES (ต้องมี 16 ตัวอักษร)
    private static HashMap<String, String> users = new HashMap<>();

    static {
        loadUsers();
    }

    /**
     * โหลดข้อมูลผู้ใช้จากไฟล์และถอดรหัส
     */
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

    /**
     * บันทึกข้อมูลผู้ใช้ลงไฟล์โดยเข้ารหัสข้อมูลก่อนบันทึก
     */
    public static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String json = new Gson().toJson(users);
            String encryptedData = encrypt(json);
            writer.write(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ลงทะเบียนผู้ใช้ใหม่
     * 
     * @param username ชื่อผู้ใช้
     * @param password รหัสผ่าน
     * @return true ถ้าสมัครสำเร็จ, false ถ้าชื่อผู้ใช้มีอยู่แล้ว
     */
    public static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // มีชื่อผู้ใช้อยู่แล้ว
        }
        users.put(username, encrypt(password));
        saveUsers();
        return true;
    }

    /**
     * ตรวจสอบข้อมูลการเข้าสู่ระบบ
     * 
     * @param username ชื่อผู้ใช้
     * @param password รหัสผ่าน
     * @return true ถ้าข้อมูลถูกต้อง, false ถ้าไม่ถูกต้อง
     */
    public static boolean validateUser(String username, String password) {
        return users.containsKey(username) && decrypt(users.get(username)).equals(password);
    }

    /**
     * โหลดรายการ To-Do ของผู้ใช้
     * 
     * @param username ชื่อผู้ใช้
     * @return รายการ To-Do ของผู้ใช้
     */
    public static List<ToDoItem> loadUserTodos(String username) {
        return SaveManager.loadUserData(username);
    }

    /**
     * บันทึก To-Do List ของผู้ใช้
     * 
     * @param username ชื่อผู้ใช้
     * @param todoList รายการ To-Do ที่ต้องการบันทึก
     */
    public static void saveUserTodos(String username, List<ToDoItem> todoList) {
        SaveManager.saveUserData(username, todoList);
    }

    /**
     * ฟังก์ชันเข้ารหัสข้อความโดยใช้ AES
     * 
     * @param data ข้อความที่ต้องการเข้ารหัส
     * @return ข้อความที่ถูกเข้ารหัส
     */
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

    /**
     * ฟังก์ชันถอดรหัสข้อความที่ถูกเข้ารหัส AES
     * 
     * @param encryptedData ข้อความที่ถูกเข้ารหัส
     * @return ข้อความที่ถูกถอดรหัส
     */
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
