package projectabc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NotificationTest extends ApplicationTest {
    private List<ToDoItem> todoList;

    @BeforeEach
    void setUp() {
        todoList = new ArrayList<>();
    }

    @Test
    void testShowReminder_NoAlertForDistantTask() {
        todoList.add(new ToDoItem("Future Task", LocalDate.now().plusDays(10), javafx.scene.paint.Color.BLACK));
        assertDoesNotThrow(() -> Notification.showReminder(todoList));
    }

}