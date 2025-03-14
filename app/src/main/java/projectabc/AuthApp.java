package projectabc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

/**
 * คลาส AuthApp จัดการการตรวจสอบตัวตนของผู้ใช้ รวมถึงการเข้าสู่ระบบและการลงทะเบียน
 */
public class AuthApp {

    /**
     * คอนสตรักเตอร์สำหรับสร้างหน้าล็อกอินเมื่อเริ่มต้นโปรแกรม
     * 
     * @param primaryStage stage หลักของแอปพลิเคชัน
     */
    public AuthApp(Stage primaryStage) {
        getLoginScene(primaryStage);
    }

    /**
     * สร้างและตั้งค่าหน้าล็อกอิน
     * 
     * @param primaryStage stage หลักที่ใช้แสดงหน้าล็อกอิน
     */
    void getLoginScene(Stage primaryStage) {
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        usernameField.setId("usernameField");
        passwordField.setId("passwordField");
        loginButton.setId("loginButton");
        registerButton.setId("registerButton");

        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText(), primaryStage));
        registerButton.setOnAction(e -> primaryStage.setScene(getRegisterScene(primaryStage)));

        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, loginButton, registerButton);
        layout.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(layout, 300, 300));
        primaryStage.show();
    }

    /**
     * สร้างและคืนค่าฉากสำหรับการลงทะเบียน
     * 
     * @param primaryStage stage หลักที่ใช้แสดงหน้าลงทะเบียน
     * @return ฉากลงทะเบียน
     */
    private Scene getRegisterScene(Stage primaryStage) {
        Label userLabel = new Label("New Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("New Password:");
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        usernameField.setId("usernameFieldReg");
        passwordField.setId("passwordFieldReg");
        backButton.setId("backButton");
        registerButton.setId("registerButtonReg");

        registerButton.setOnAction(e -> handleRegister(usernameField.getText(), passwordField.getText(), primaryStage));
        backButton.setOnAction(e -> getLoginScene(primaryStage));

        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, registerButton, backButton);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout, 300, 300);
    }

    /**
     * จัดการการลงทะเบียนของผู้ใช้โดยบันทึกข้อมูลบัญชีใหม่
     * 
     * @param username ชื่อผู้ใช้ที่ป้อน
     * @param password รหัสผ่านที่ป้อน
     * @param primaryStage stage หลัก ใช้สำหรับเปลี่ยนกลับไปยังหน้าล็อกอินหากลงทะเบียนสำเร็จ
     */
    private void handleRegister(String username, String password, Stage primaryStage) {
        if (Database.registerUser(username, password)) {
            showAlert("Success", "Registration successful!");
            getLoginScene(primaryStage);
        } else {
            showAlert("Error", "Username already exists!");
        }
    }

    /**
     * จัดการการเข้าสู่ระบบของผู้ใช้โดยตรวจสอบข้อมูลบัญชีและโหลดหน้าหลัก
     * 
     * @param username ชื่อผู้ใช้ที่ป้อน
     * @param password รหัสผ่านที่ป้อน
     * @param primaryStage stage หลักที่ใช้แสดงหน้าแดชบอร์ดหากเข้าสู่ระบบสำเร็จ
     */
    private void handleLogin(String username, String password, Stage primaryStage) {
        if (Database.validateUser(username, password)) {
            List<ToDoItem> todoList = Database.loadUserTodos(username);
            new View(primaryStage, username, todoList).showDashboard();
        } else {
            showAlert("Error", "Invalid username or password!");
        }
    }

    /**
     * แสดงกล่องข้อความแจ้งเตือนพร้อมหัวข้อและข้อความที่กำหนด
     * 
     * @param title หัวข้อของการแจ้งเตือน
     * @param message ข้อความที่ต้องการแสดงในกล่องแจ้งเตือน
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
