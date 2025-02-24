package projectabc;
// import javafx.scene.paint.Color;
// import java.util.ArrayList;
// import java.util.List;

// public class Tag {
//     private String name;
//     private Color color;
//     private static List<Tag> tagList = new ArrayList<>();

//     public Tag(String name, Color color) {
//         this.name = name;
//         this.color = color;
//         tagList.add(this);
//     }

//     public String getName() { return name; }
//     public Color getColor() { return color; }

//     public static List<Tag> getAvailableTags() { return tagList; }

//     static {
//         new Tag("Urgent", Color.RED);
//         new Tag("Work", Color.BLUE);
//         new Tag("Personal", Color.GREEN);
//     }
//     private void updateSelectedItemColor() {
//         int selectedIndex = listView.getSelectionModel().getSelectedIndex();
//         if (selectedIndex >= 0) {
//             Text selectedItem = observableItems.get(selectedIndex);
//             String selectedColor = colorComboBox.getValue();
//             Color color = switch (selectedColor) {
//                 case "Red" -> Color.RED;
//                 case "Blue" -> Color.BLUE;
//                 case "Green" -> Color.GREEN;
//                 default -> Color.BLACK;
//             };
//             selectedItem.setFill(color);
//         }
//     }
// }
