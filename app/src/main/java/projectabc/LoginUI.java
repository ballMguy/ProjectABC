package projectabc;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI {
    public LoginUI(Stage primaryStage) {
        VBox root = new VBox(8);
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Label messageLabel = new Label();

        usernameField.setId("usernameField");
        passwordField.setId("passwordField");
        loginButton.setId("loginButton");
        messageLabel.setId("messageLabel");

        loginButton.setOnAction(e -> {
            User user = Database.authenticate(usernameField.getText(), passwordField.getText());
            if (user != null) new DashboardUI(primaryStage, user);
            else messageLabel.setText("Invalid credentials.");
        });

        registerButton.setOnAction(e -> new RegisterUI(primaryStage));

        root.getChildren().addAll(new Label("Username:"), usernameField, new Label("Password:"), passwordField, loginButton, registerButton, messageLabel);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }
}