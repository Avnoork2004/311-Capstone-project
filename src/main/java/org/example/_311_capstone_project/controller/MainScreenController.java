package org.example._311_capstone_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainScreenController {

    @FXML
    private Button logoutButton;

    @FXML
    void handleLogout(ActionEvent event) {
        System.out.println("Logout logic here");
    }
}
