package projectabc;

// public class Task {
//     private String name;
//     private String cat;

//     public Task(String name, String cat) {
//         this.name = name;
//         this.cat = cat;
//     }
// }
import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String title;
    private boolean done;
    private Date dueDate;
    private Tag tag;

    public Task(String title) {
        this.title = title;
        this.done = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        return new Date().after(dueDate);
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
