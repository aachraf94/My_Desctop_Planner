module com.example.my_desktop_planner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.my_desktop_planner to javafx.fxml;
    exports com.example.my_desktop_planner;
}