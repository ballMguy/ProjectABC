package projectabc;
import javafx.scene.paint.Color;
import java.time.LocalDate;

public class ToDoItem {
    private String task;
    private LocalDate deadline;
    private Color color; // เพิ่มฟิลด์สำหรับสี

    public ToDoItem(String task, LocalDate deadline, Color color) {
        this.task = task;
        this.deadline = deadline;
        this.color = color;
    }

    public String getTask() {
        return task;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return task + " (Due: " + deadline + ")";
    }
}
