package org.example._311_capstone_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

// Controller for handling user signup operations and navigating back to the login screen
public class SignupController {

    @FXML
    private Text confirmPass;

    @FXML
    private PasswordField confirmpassfield;

    @FXML
    private Text email;

    @FXML
    private TextField emailText;

    @FXML
    private Text firstName;

    @FXML
    private TextField firstNameText;

    @FXML
    private Text lastName;

    @FXML
    private TextField lastNameText;

    @FXML
    private Button logIn;

    @FXML
    private Text pass;

    @FXML
    private PasswordField passfeild;

    @FXML
    private Button signUp;

    @FXML
    private Text user;

    @FXML
    private TextField userText;


    // Event handler to return to the login screen
    @FXML
    void backToLoginPage(ActionEvent event) {
        try {
            // Loads login.fxml for the login screen
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/login.fxml"));
            Parent loginRoot = fxmlLoader.load();

            // Sets the current stage to show the login screen
            Stage stage = (Stage) logIn.getScene().getWindow();
            Scene scene = new Scene(loginRoot, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method to store the signup data to the database
    @FXML
    void storeSignupDatabase(ActionEvent event) {
        // Logic to store the signup information to the database will be added
    }
}
