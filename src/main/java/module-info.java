module org.example._311_capstone_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example._311_capstone_project to javafx.fxml;
    //exports org.example._311_capstone_project;
    exports org.example._311_capstone_project.controller;
    opens org.example._311_capstone_project.controller to javafx.fxml;
}