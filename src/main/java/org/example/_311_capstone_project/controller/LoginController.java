package org.example._311_capstone_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

// Controller for the login screen navigating to the Signup or Main Screen
public class LoginController {

    @FXML
    private Button login;

    @FXML
    private Button signup;

    // Event handler for navigating to the signup screen
    @FXML
    void signupbtn(ActionEvent event) {
        try {
            // Loads signup.fxml for the signup screen
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/signup.fxml"));
            Parent signupRoot = fxmlLoader.load();

            // Gets the current stage and sets it to the signup scene
            Stage stage = (Stage) signup.getScene().getWindow();
            Scene scene = new Scene(signupRoot, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Event handler for navigating to the main screen after the login page
    @FXML
    void loginbtn(ActionEvent event) {
        try {
            // Loads mainscreen.fxml for the main application screen
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/mainscreen.fxml"));
            Parent mainScreenRoot = fxmlLoader.load();

            // Gets the current stage and sets it to the main screen scene
            Stage stage = (Stage) login.getScene().getWindow();
            Scene scene = new Scene(mainScreenRoot, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Main Screen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
