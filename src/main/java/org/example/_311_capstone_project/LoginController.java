package org.example._311_capstone_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    void signupbtn(ActionEvent event) {
        try {
            // Load signup.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent signupRoot = fxmlLoader.load();

            // Get the current stage from the signup button
            Stage stage = (Stage) signup.getScene().getWindow();

            // Set the new scene with signup.fxml
            Scene scene = new Scene(signupRoot, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void loginbtn(ActionEvent event) {
        try {
            // Load mainscreen.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainscreen.fxml"));
            Parent mainScreenRoot = fxmlLoader.load();

            // Get the current stage from the button
            Stage stage = (Stage) login.getScene().getWindow();

            // Set the new scene with mainscreen.fxml
            Scene scene = new Scene(mainScreenRoot, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Main Screen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
