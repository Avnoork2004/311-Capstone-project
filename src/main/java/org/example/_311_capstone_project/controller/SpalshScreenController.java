package org.example._311_capstone_project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SpalshScreenController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
    }
}