package projectabc;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
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
        Button logoutButton = new Button("Logout");
        Button manageButton = new Button("Manage To-Do List");

        logoutButton.setOnAction(e -> {
            SaveManager.saveUserData(username, todoList);
            primaryStage.setScene(new AuthApp().getLoginScene());
        });

        manageButton.setOnAction(e -> new Manager(primaryStage, username, todoList).showManager());

        VBox layout = new VBox(10, userLabel, manageButton, logoutButton);
        layout.setSpacing(10);
        primaryStage.setScene(new Scene(layout, 400, 300));

        Notification.showReminder(todoList);
    }
}
