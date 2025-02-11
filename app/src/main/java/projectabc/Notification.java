package projectabc;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.paint.Color;

public class Notification {
    public static void showReminder(List<ToDoItem> todos) {
        LocalDate today = LocalDate.now();
        for (ToDoItem item : todos) {
            if (item.getDeadline() != null && item.getDeadline().isBefore(today.plusDays(5))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upcoming Deadline");
                alert.setHeaderText("You have a task due soon!");
                alert.setContentText("Task: " + item.getTask() + "\nDue Date: " + item.getDeadline());
                alert.show();
                break;
            }
        }
    }
}
