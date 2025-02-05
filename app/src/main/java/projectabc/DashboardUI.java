package projectabc;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardUI {
    public DashboardUI(Stage primaryStage, User user) {
        VBox root = new VBox(10);
        Label userLabel = new Label("Hello " + user.getUsername() + "!");

        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");

        

        depositButton.setOnAction(e -> {
            // double amount = Double.parseDouble(amountField.getText());
            // if (amount > 0) {  // ตรวจสอบว่าต้องเป็นค่าบวกเท่านั้น
            //     user.deposit(amount);
            //     balanceLabel.setText("Balance: $" + user.getBalance());
            // } else {
            //     balanceLabel.setText("Invalid deposit amount.");
            // }
        });

        withdrawButton.setOnAction(e -> {
            // double amount = Double.parseDouble(amountField.getText());
            // if (user.withdraw(amount)) balanceLabel.setText("Balance: $" + user.getBalance());
            // else balanceLabel.setText("Insufficient funds.");
        });

        root.getChildren().addAll(userLabel,depositButton, withdrawButton);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }
}
