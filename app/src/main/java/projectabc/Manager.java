package projectabc;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Manager {
    private ArrayList<Task> tasks;
    private Save saveManager;
    private Notification notification;

    public Manager() {
        tasks = new ArrayList<>();
        saveManager = new Save();
        notification = new Notification();
        loadTasks();
    }

    public VBox createMainView() {
        VBox layout = new VBox();
        ListView<Task> taskListView = new ListView<>();

        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(e -> addTask());

        layout.getChildren().addAll(taskListView, addTaskButton);

        return layout;
    }

    public void addTask() {
        // Logic for adding a task
        Task newTask = new Task("New Task");
        tasks.add(newTask);
        saveManager.saveTasks(tasks);
    }

    public void loadTasks() {
        tasks = saveManager.loadTasks();
    }
}
