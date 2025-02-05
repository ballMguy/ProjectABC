package projectabc;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterUI {
    public RegisterUI(Stage primaryStage) {
        VBox root = new VBox(10);
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Register");
        Label messageLabel = new Label();

        usernameField.setId("usernameFieldReg");
        passwordField.setId("passwordFieldReg");
        registerButton.setId("registerButtonReg");
        messageLabel.setId("messageLabelReg");

        registerButton.setOnAction(e -> {
            if (Database.registerUser(usernameField.getText(), passwordField.getText()))
                new LoginUI(primaryStage);
            else messageLabel.setText("Username already taken.");
        });

        root.getChildren().addAll(new Label("Username:"), usernameField, new Label("Password:"), passwordField, registerButton, messageLabel);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }
}