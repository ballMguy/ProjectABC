package projectabc;
import java.time.LocalDate;
import javafx.scene.paint.Color;

public class ToDoItem {
    private String task;
    private LocalDate deadline;
    private Tag tag;

    public ToDoItem(String task, LocalDate deadline, Tag tag) {
        this.task = task;
        this.deadline = deadline;
        this.tag = tag;
    }

    public String getTask() { return task; }
    public LocalDate getDeadline() { return deadline; }
    public Tag getTag() { return tag; }

    @Override
    public String toString() {
        return task + " (" + deadline + ") - " + tag.getName();
    }
}
