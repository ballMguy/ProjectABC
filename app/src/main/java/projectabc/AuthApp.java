package projectabc;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthApp extends Application {

    
    private Stage primaryStage;
    

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Login System");
        stage.setScene(getLoginScene());
        stage.show();
    }

    Scene getLoginScene() {
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText()));
        registerButton.setOnAction(e -> primaryStage.setScene(getRegisterScene()));

        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, loginButton, registerButton);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout, 300, 300);
    }

    private Scene getRegisterScene() {
        Label userLabel = new Label("New Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("New Password:");
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        registerButton.setOnAction(e -> handleRegister(usernameField.getText(), passwordField.getText()));
        backButton.setOnAction(e -> primaryStage.setScene(getLoginScene()));

        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, registerButton, backButton);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout, 300, 300);
    }

    private void handleRegister(String username, String password) {
        if (Database.registerUser(username, password)) {
            showAlert("Success", "Registration successful!");
            primaryStage.setScene(getLoginScene());
        } else {
            showAlert("Error", "Username already exists!");
        }
    }
    
    private void handleLogin(String username, String password) {
        if (Database.validateUser(username, password)) {
            List<ToDoItem> todoList = Database.loadUserTodos(username);
            new View(primaryStage, username, todoList).showDashboard();
        } else {
            showAlert("Error", "Invalid username or password!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
