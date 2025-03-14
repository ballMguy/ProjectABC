package projectabc;

import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.util.List;

/**
 * คลาสสำหรับแจ้งเตือนผู้ใช้เกี่ยวกับงานที่มีกำหนดส่งใกล้ถึง
 */
public class Notification {
    
    /**
     * แสดงการแจ้งเตือนหากมีงานที่มีกำหนดส่งภายใน 5 วัน
     * 
     * @param todos รายการงานที่ต้องทำ
     */
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
