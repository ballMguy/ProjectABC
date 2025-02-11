package projectabc;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;

public class Manager {
    private Stage primaryStage;
    private List<ToDoItem> todoList;

    public Manager(Stage stage, String user, List<ToDoItem> todos) {
        this.primaryStage = stage;
        this.todoList = todos;
    }

    public void showManager() {
        ListView<ToDoItem> listView = new ListView<>();
        listView.getItems().addAll(todoList);

        TextField taskField = new TextField();
        DatePicker deadlinePicker = new DatePicker();
        ComboBox<Tag> tagBox = new ComboBox<>();
        tagBox.getItems().addAll(Tag.getAvailableTags());

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            ToDoItem newItem = new ToDoItem(taskField.getText(), deadlinePicker.getValue(), tagBox.getValue());
            todoList.add(newItem);
            listView.getItems().add(newItem);
        });

        VBox layout = new VBox(10, listView, taskField, deadlinePicker, tagBox, addButton);
        primaryStage.setScene(new Scene(layout, 500, 400));
    }
}

