package projectabc;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

/**
 * คลาส View ใช้สำหรับแสดงหน้าหลักของแอปพลิเคชัน โดยแสดง To-Do List ของผู้ใช้
 * และให้สามารถไปยังหน้าจัดการ To-Do List ได้
 */
public class View {
    private Stage primaryStage;
    private String username;
    private List<ToDoItem> todoList;

    /**
     * สร้างอินสแตนซ์ของ View
     * 
     * @param stage เวทีหลักของ JavaFX
     * @param user ชื่อผู้ใช้ที่เข้าสู่ระบบ
     * @param todos รายการสิ่งที่ต้องทำของผู้ใช้
     */
    public View(Stage stage, String user, List<ToDoItem> todos) {
        this.primaryStage = stage;
        this.username = user;
        this.todoList = todos;
    }

    /**
     * แสดงหน้าหลัก (Dashboard) ซึ่งประกอบด้วยรายการสิ่งที่ต้องทำและปุ่มสำหรับจัดการ
     */
    public void showDashboard() {
        Label userLabel = new Label("User: " + username);
        Label textLabel = new Label("รายการสิ่งที่ต้องทำ");
        Button logoutButton = new Button("Logout");
        Button manageButton = new Button("Manage To-Do List");
        manageButton.setId("manageButton");
        userLabel.setId("userLabel");
        logoutButton.setId("logoutButton");
        
        // ✅ แสดง To-Do List แต่ไม่ให้แก้ไข
        ListView<ToDoItem> listView = new ListView<>();
        listView.getItems().addAll(todoList); // โหลดข้อมูลจาก Manager
        listView.setDisable(false); // ทำให้เป็น Read-Only
        listView.setId("listView");

        logoutButton.setOnAction(e -> {
            SaveManager.saveUserData(username, todoList);
            AuthApp authApp = new AuthApp(this.primaryStage);
            authApp.getLoginScene(this.primaryStage);
        });

        manageButton.setOnAction(e -> new Manager(primaryStage, username, todoList).showManager());

        VBox layout = new VBox(10, userLabel, textLabel, listView, manageButton, logoutButton);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(layout, 400, 300));

        // แสดงการแจ้งเตือนสำหรับงานที่ใกล้ถึงกำหนด
        Notification.showReminder(todoList);

        // กำหนดรูปแบบการแสดงผลของรายการ To-Do
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ToDoItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTextFill(null);
                } else {
                    setText(item.getTask() + " (Due: " + item.getDeadline() + ")");
                    setTextFill(item.getColor());
                }
            }
        });
    }
}
