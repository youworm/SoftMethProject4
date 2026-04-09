module org.example.project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.project4 to javafx.fxml;
    exports org.example.project4;
    exports org.example.project4.backend;
    opens org.example.project4.backend to javafx.fxml;
}