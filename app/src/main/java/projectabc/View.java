package projectabc;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class View {
    private Stage primaryStage;
    private String username;
    private List<ToDoItem> todoList;

    public View(Stage stage, String user, List<ToDoItem> todos) {
        this.primaryStage = stage;
        this.username = user;
        this.todoList = todos;
    }

    public void showDashboard() {
    Label userLabel = new Label("User: " + username);
    Label textLabel = new Label("รายการสิ่งที่ต้องทำ");
    Button logoutButton = new Button("Logout");
    Button manageButton = new Button("Manage To-Do List");

    // ✅ แสดง To-Do List แต่ไม่ให้แก้ไข
    ListView<ToDoItem> listView = new ListView<>();
    listView.getItems().addAll(todoList);  // โหลดข้อมูลจาก Manager
    listView.setDisable(false); // ทำให้เป็น Read-Only

    logoutButton.setOnAction(e -> {
        SaveManager.saveUserData(username, todoList);
        AuthApp authApp = new AuthApp();
        primaryStage.setScene(authApp.getLoginScene());
    });

    manageButton.setOnAction(e -> new Manager(primaryStage, username, todoList).showManager());

    VBox layout = new VBox(10, userLabel, textLabel, listView, manageButton, logoutButton);
    layout.setSpacing(10);
    layout.setAlignment(Pos.CENTER);
    
    primaryStage.setScene(new Scene(layout, 400, 300));

    Notification.showReminder(todoList);
}

}
