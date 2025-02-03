Project_CCCP
⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠤⠤⠒⠖⠒⠦⠤⠤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⡠⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠲⢄⡀⠀⠀⠀⠀
⠀⠀⠀⠀⡠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢄⠀⠀⠀
⠀⠀⢀⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⡀⠀
⠀⠀⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⢳⠀
⠀⢸⠃⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⢀⡴⠾⠆⠀⠀⠀⠈⠈⠁⠀⠀⠀⠈⡆
⠀⡾⠀⠀⠀⠀⠀⠐⢛⡿⠿⠟⠻⠿⡿⠛⠀⠀⠀⠛⣿⠿⠛⠛⠻⠂⠀⠀⢸
⢰⡇⠀⠀⠀⠀⠀⣀⣾⣗⠦⠀⠀⠈⡿⠀⠀⣼⠀⠀⣿⡀⠀⠐⢄⠆⠀⠀⠸
⢸⠀⠀⠀⠀⠀⠀⠀⢹⣿⣷⣶⣴⢞⠕⠀⠀⣹⡆⠀⠙⣿⣷⣾⡟⠀⠀⠀⢸
⢸⡀⠀⣀⠤⠀⠄⠀⠀⠙⠟⠋⢁⣤⣼⣅⣶⣾⡿⣶⡶⢏⠉⠉⠀⠐⠢⠀⡇
⠈⢧⠰⠁⠀⣀⢠⡔⠒⢾⠿⢿⣿⣿⡿⠭⠭⡭⠦⠦⠭⠿⢿⡶⠆⣠⢠⠃
⠀⠈⢆⠀⠀⢏⡉⠀⠀⠀⠀⠀⠈⠉⠛⢛⡿⠯⠭⠙⠛⠉⠁⠀⠀⡤⠜⡜⠀
⠀⠀⠀⠳⣄⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠁⠂⠓⡄⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀
⠀⠀⠀⠀⠈⠳⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠀⠀⠀⠀⢀⡤⠁⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠈⠑⠢⠤⣀⡀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠒⠉⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠙⠒⠒⠒⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀
1. Main.java
java
คัดลอก
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        View view = new View();
        Scene scene = new Scene(view.createMainView(), 800, 600);
        
        primaryStage.setTitle("JavaFX Program");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
2. Manager.java
java
คัดลอก
public class Manager {
    private User user;
    private Save save;

    public Manager() {
        this.user = new User("Default User");
        this.save = new Save();
    }

    public void addNotification(Notification notification) {
        save.saveNotification(notification);
    }

    public void changeUser(String username) {
        this.user = new User(username);
    }

    public User getUser() {
        return user;
    }
}
3. Notification.java
java
คัดลอก
public class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Notification: " + message;
    }
}
4. Save.java
java
คัดลอก
import java.util.ArrayList;
import java.util.List;

public class Save {
    private List<Notification> notifications;

    public Save() {
        this.notifications = new ArrayList<>();
    }

    public void saveNotification(Notification notification) {
        notifications.add(notification);
        System.out.println("Notification saved: " + notification.getMessage());
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
}
5. Tag.java
java
คัดลอก
public class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
6. View.java
java
คัดลอก
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {

    public VBox createMainView() {
        VBox layout = new VBox(20);

        Button btnAddNotification = new Button("Add Notification");
        btnAddNotification.setOnAction(e -> addNotification());

        Label label = new Label("Welcome to the App!");

        layout.getChildren().addAll(label, btnAddNotification);

        return layout;
    }

    private void addNotification() {
        Manager manager = new Manager();
        Notification notification = new Notification("This is a new notification.");
        manager.addNotification(notification);
    }
}
7. User.java
java
คัดลอก
public class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
