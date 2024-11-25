package org.example._311_capstone_project.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Controller for handling user signup operations and navigating back to the login screen
public class SignupController {

    @FXML
    private Text confirmPass;

    @FXML
    private PasswordField confirmpassField;

    @FXML
    private Text email;

    @FXML
    private TextField emailField;

    @FXML
    private Text firstName;

    @FXML
    private TextField firstNameField;

    @FXML
    private Text lastName;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button logIn;

    @FXML
    private Text pass;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signUp;

    @FXML
    private Text user;

    @FXML
    private TextField userField;

    @FXML
    private Label validationMessage;


    // Regex for validation (checked with regex101)
    private final String nameRegex = "^[a-zA-Z]{2,25}$";
    private final String userRegex = "^[a-zA-Z0-9]{3,20}$";  // Alphanumeric username (3-20 characters)
    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$"; // Standard email format
    private final String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"; // At least 1 upper, 1 lower, 1 digit, 8+ chars


    @FXML
    public void initialize() {
        // Add listeners for each field for focus change (showing & not showing button)
        addFocusListeners();

        // BooleanBinding to check all fields are valid
        BooleanBinding isFormValid = Bindings.createBooleanBinding(() ->
                        !firstNameField.getText().matches(nameRegex) ||
                                !lastNameField.getText().matches(nameRegex) ||
                                !emailField.getText().matches(emailRegex) ||
                                !userField.getText().matches(userRegex) ||
                                !passField.getText().matches(passRegex) ||
                                !confirmpassField.getText().matches(passRegex),
                firstNameField.textProperty(),
                lastNameField.textProperty(),
                emailField.textProperty(),
                userField.textProperty(),
                passField.textProperty(),
                confirmpassField.textProperty()

        );

        // Disables "Add" button if a field is not valid
        signUp.disableProperty().bind(isFormValid);
        signUp.setOnAction(this::storeSignupDatabase);
    }


    // Event handler to return to the login screen
    @FXML
    void backToLoginPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/login.fxml"));
            Parent loginRoot = fxmlLoader.load();

            // Sets the current stage to show the login screen
            Stage stage = (Stage) logIn.getScene().getWindow();
            Scene scene = new Scene(loginRoot, 895, 650);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Store the signup information in the preferences database if valid
    /*@FXML
    private void storeSignupDatabase(ActionEvent event) {
        if (!signUp.isDisabled()) {
            validationMessage.setText("Registration Successful!"); //  success message moved to new gui
            //loadAnotherView(); // Calls method to load new view fxml
        } else {
            validationMessage.setText("Please correct the invalid fields."); // Displays error if form is invalid
        }
    }
    */

    //creates an account and stores credentials into preference file
    public void storeSignupDatabase(ActionEvent actionEvent) {
        // Retrieve input from fields
        String username = userField.getText();
        String password = passField.getText();
        String email = emailField.getText();

        // Check validity again before saving (if needed)
        if (!username.matches(userRegex)) {
            validationMessage.setText("Invalid username.");
            return;
        }
        if (!password.matches(passRegex)) {
            validationMessage.setText("Invalid password.");
            return;
        }
        if (!confirmpassField.getText().equals(password)) { // Fixed line
            validationMessage.setText("Passwords do not match.");
            return;
        }
        if (!email.matches(emailRegex)) {
            validationMessage.setText("Invalid email.");
            return;
        }

        try {
            // Store data in Preferences
            Preferences userPreferences = Preferences.userRoot().node(this.getClass().getName());
            userPreferences.put("USERNAME", username);
            userPreferences.put("PASSWORD", password);
            userPreferences.put("EMAIL", email);

            // Set the current user session
            UserSession.getInstance(username, password);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account Created");
            alert.setContentText("Your account has been successfully created and stored.");
            alert.showAndWait();

            // Redirect to login screen
            backToLoginPage(actionEvent);

        } catch (Exception e) {
            e.printStackTrace();
            validationMessage.setText("Error storing account information. Please try again.");
        }
    }

    private void addFocusListeners() {
        firstNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Focus lost ( goes away)
                checkValidity(firstNameField, nameRegex, "First Name");
            }
        });

        lastNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Focus lost ( goes away)
                checkValidity(lastNameField, nameRegex, "Last Name");
            }
        });

        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Focus lost ( goes away)
                checkValidity(emailField, emailRegex, "Email");
            }
        });

        userField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Focus lost ( goes away)
                checkValidity(userField, userRegex, "User Name");
            }
        });

        passField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Focus lost ( goes away)
                checkValidity(passField, passRegex, "password");
            }
        });

        confirmpassField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Focus lost ( goes away)
                checkValidity(confirmpassField, passRegex, "Confirm password");
            }
        });
    }

    // checks if valid input for specific field using regular expression and updates validation message
    private void checkValidity(TextField field, String regex, String fieldName) {
        if (field.getText().matches(regex)) {
            validationMessage.setText(fieldName + " is valid.");
        } else {
            validationMessage.setText(fieldName + " is invalid.");
        }
    }
}
