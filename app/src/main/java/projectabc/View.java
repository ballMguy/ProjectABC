package projectabc;

import javafx.scene.control.ListView;

public class View {
    private ListView<Task> taskListView;

    public View() {
        taskListView = new ListView<>();
    }

    public ListView<Task> getTaskListView() {
        return taskListView;
    }

    public void displayTasks(ArrayList<Task> tasks) {
        taskListView.getItems().setAll(tasks);
    }
}
