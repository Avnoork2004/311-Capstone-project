package org.example._311_capstone_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private Text confirmPass;

    @FXML
    private TextField confirmPassText;

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
    private TextField passText;

    @FXML
    private Button signUp;

    @FXML
    private Text user;

    @FXML
    private TextField userText;

    @FXML
    void backToLoginPage(ActionEvent event) {
        try {
            // Load login.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent loginRoot = fxmlLoader.load();

            // Get the current stage from the logIn button
            Stage stage = (Stage) logIn.getScene().getWindow();

            // Set the new scene with login.fxml
            Scene scene = new Scene(loginRoot, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void storeSignupDatabase(ActionEvent event) {

    }

}