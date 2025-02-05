package projectabc;

import java.util.Timer;
import java.util.TimerTask;

public class Notification {
    private Timer timer;

    public Notification() {
        timer = new Timer(true);
    }

    public void scheduleNotification(Task task) {
        TimerTask notificationTask = new TimerTask() {
            @Override
            public void run() {
                if (task.isOverdue()) {
                    System.out.println("Task " + task.getTitle() + " is overdue!");
                }
            }
        };
        timer.schedule(notificationTask, task.getDueDate());
    }
}
