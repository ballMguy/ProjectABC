package projectabc;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.paint.Color;

public class Manager {
    private Stage primaryStage;
    private List<ToDoItem> todoList;
    private String username;

    public Manager(Stage stage, String user, List<ToDoItem> todos) {
        this.primaryStage = stage;
        this.username = user;
        this.todoList = todos;
    }

    public void showManager() {
        // ชื่อผู้ใช้มุมซ้าย
        Label userLabel = new Label("User: " + username);

        // ปุ่มย้อนกลับไป View.java
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new View(primaryStage, username, todoList).showDashboard());

        // Layout บนสุด
        HBox topBar = new HBox(userLabel, backButton);
        topBar.setSpacing(10);
        topBar.setAlignment(Pos.CENTER_RIGHT);

        // ListView แสดง To-Do
        ListView<ToDoItem> listView = new ListView<>();
        listView.getItems().addAll(todoList);

        // ช่องกรอกข้อมูล To-Do ใหม่
        TextField taskField = new TextField();
        DatePicker deadlinePicker = new DatePicker();
        ComboBox<Tag> tagBox = new ComboBox<>();
        tagBox.getItems().addAll(Tag.getAvailableTags());

        // ปุ่มเพิ่มรายการ
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            if (!taskField.getText().isEmpty() && deadlinePicker.getValue() != null) {
                ToDoItem newItem = new ToDoItem(taskField.getText(), deadlinePicker.getValue(), tagBox.getValue());
                todoList.add(newItem);
                listView.getItems().add(newItem);
                SaveManager.saveUserData(username, todoList); // บันทึกข้อมูล
            }
        });

        // ปุ่มลบรายการ
        Button deleteButton = new Button("Delete Selected");
        deleteButton.setOnAction(e -> {
            ToDoItem selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                todoList.remove(selectedItem);
                listView.getItems().remove(selectedItem);
                SaveManager.saveUserData(username, todoList); // บันทึกข้อมูล
            }
        });

        // Layout ส่วนกลาง
        VBox centerLayout = new VBox(10, listView, deleteButton, taskField, deadlinePicker, tagBox, addButton);
        centerLayout.setAlignment(Pos.CENTER);

        // Layout หลัก
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topBar);
        mainLayout.setCenter(centerLayout);

        // แสดง Scene
        primaryStage.setScene(new Scene(mainLayout, 500, 400));
    }
}
