package org.example._311_capstone_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (validateLogin(username, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainscreen.fxml"));
                Scene mainScene = new Scene(loader.load());
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(mainScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid credentials");
        }
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
            Scene signUpScene = new Scene(loader.load());
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            stage.setScene(signUpScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateLogin(String username, String password) {
        // Add your authentication logic here
        return username.equals("admin") && password.equals("password");
    }
}
