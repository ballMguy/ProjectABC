package projectabc;
// import javafx.application.Application;
// import javafx.stage.Stage;

// public class Main extends Application {
//     @Override
//     public void start(Stage primaryStage) {
//         new LoginUI(primaryStage);
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Manager manager = new Manager();
        VBox root = manager.createMainView();

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

