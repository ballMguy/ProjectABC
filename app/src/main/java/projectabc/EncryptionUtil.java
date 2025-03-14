package projectabc;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * คลาสสำหรับเข้ารหัสและถอดรหัสข้อความโดยใช้ AES
 */
public class EncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456"; // ต้องมี 16 ตัวอักษร (คีย์ขนาด 128 บิต)

    /**
     * เข้ารหัสข้อความโดยใช้ AES
     * 
     * @param data ข้อความที่ต้องการเข้ารหัส
     * @return ข้อความที่ถูกเข้ารหัสในรูปแบบ Base64
     * @throws Exception หากเกิดข้อผิดพลาดในการเข้ารหัส
     */
    public static String encrypt(String data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * ถอดรหัสข้อความที่ถูกเข้ารหัสด้วย AES
     * 
     * @param encryptedData ข้อความที่ถูกเข้ารหัสในรูปแบบ Base64
     * @return ข้อความที่ถูกถอดรหัส
     * @throws Exception หากเกิดข้อผิดพลาดในการถอดรหัส
     */
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted);
    }
}
