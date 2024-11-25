package org.example._311_capstone_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

// Controller for the login screen navigating to the Signup or Main Screen
public class LoginController {

    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;

    // Event handler for navigating to the signup screen
    @FXML
    void signupbtn(ActionEvent event) {
        try {
            // Loads signup.fxml for the signup screen
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/signup.fxml"));
            Parent signupRoot = fxmlLoader.load();

            // Gets the current stage and sets it to the signup scene
            Stage stage = (Stage) signup.getScene().getWindow();
            Scene scene = new Scene(signupRoot, 895, 650);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Event handler for navigating to the main screen after the login page
    /*@FXML
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
*/
    //gets the stored info from signup
    @FXML
    public void loginbtn(ActionEvent actionEvent) {
        // Retrieve input from username and password fields
        String enteredUsername = userField.getText();
        String enteredPassword = passField.getText();

        // Access stored credentials from Preferences
        Preferences userPreferences = Preferences.userRoot().node(SignupController.class.getName());
        String storedUsername = userPreferences.get("USERNAME", null);
        String storedPassword = userPreferences.get("PASSWORD", null);

        if (storedUsername == null || storedPassword == null) {
            // No user account exists
            showErrorAlert("No account found. Please sign up first.");
            return;
        }

        // Validate credentials
        if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
            // Login successful, set user session
            UserSession.getInstance(storedUsername, storedPassword);

            // Redirect to the next screen
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/org/example/_311_capstone_project/mainscreen.fxml"));
                Scene scene = new Scene(root, 600, 400);
                scene.getStylesheets().add(getClass().getResource("/org/example/_311_capstone_project/style.css").toExternalForm());
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
                showErrorAlert("Failed to load the application interface.");
            }
        } else {
            // Credentials do not match
            showErrorAlert("Invalid username or password. Please try again.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setContentText(message);
        alert.showAndWait();
    }



}
