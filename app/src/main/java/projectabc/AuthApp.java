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

public class AuthApp{

    
   
    public AuthApp(Stage primaryStage){
        getLoginScene(primaryStage);
    }
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
        
       

        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText(),primaryStage));
        registerButton.setOnAction(e -> primaryStage.setScene(getRegisterScene(primaryStage)));

        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, loginButton, registerButton);
        layout.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(layout, 300, 300));
        primaryStage.show();
    }

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

        registerButton.setOnAction(e -> handleRegister(usernameField.getText(), passwordField.getText(),primaryStage));
        backButton.setOnAction(e -> getLoginScene(primaryStage));

        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, registerButton, backButton);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout, 300, 300);
    }

    private void handleRegister(String username, String password,Stage primaryStage) {
        if (Database.registerUser(username, password)) {
            showAlert("Success", "Registration successful!");
            getLoginScene(primaryStage);
        } else {
            showAlert("Error", "Username already exists!");
        }
    }
    
    private void handleLogin(String username, String password, Stage primaryStage) {
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
