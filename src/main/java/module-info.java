module org.example._311_capstone_project.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example._311_capstone_project to javafx.fxml;
    //exports org.example._311_capstone_project;
    exports org.example._311_capstone_project.controller;
    opens org.example._311_capstone_project.controller to javafx.fxml;
}
