package projectabc;

import javafx.scene.paint.Color;
import java.time.LocalDate;

/**
 * คลาสแทนงานที่ต้องทำ (To-Do Item) ซึ่งมีรายละเอียดของงาน กำหนดส่ง และสีที่ใช้แสดงผล
 */
public class ToDoItem {
    private String task;
    private LocalDate deadline;
    private Color color; // เพิ่มฟิลด์สำหรับสี

    /**
     * สร้างวัตถุ ToDoItem
     * 
     * @param task รายละเอียดของงาน
     * @param deadline วันที่กำหนดส่ง
     * @param color สีที่ใช้แสดงผลของงาน
     */
    public ToDoItem(String task, LocalDate deadline, Color color) {
        this.task = task;
        this.deadline = deadline;
        this.color = color;
    }

    /**
     * รับรายละเอียดของงาน
     * 
     * @return รายละเอียดของงาน
     */
    public String getTask() {
        return task;
    }

    /**
     * รับวันที่กำหนดส่งของงาน
     * 
     * @return วันที่กำหนดส่งของงาน
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * รับค่าสีที่ใช้แสดงผลของงาน
     * 
     * @return สีที่ใช้แสดงผลของงาน
     */
    public Color getColor() {
        return color;
    }

    /**
     * ตั้งค่าสีที่ใช้แสดงผลของงาน
     * 
     * @param color สีใหม่ของงาน
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * แปลงข้อมูลของงานเป็นข้อความที่อ่านง่าย
     * 
     * @return ข้อความที่แสดงรายละเอียดของงานและวันที่กำหนดส่ง
     */
    @Override
    public String toString() {
        return task + " (Due: " + deadline + ")";
    }
}
