package org.example._311_capstone_project.controller;

import database.DatabaseConnection;
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
import java.sql.Connection;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import database.User;
import database.UserDAO;
import database.DatabaseConnection;

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
    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@farmingdale.edu$";
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
    @FXML
    public void storeSignupDatabase(ActionEvent actionEvent) {
        // Retrieve input from fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = userField.getText();
        String email = emailField.getText();
        String password = passField.getText();
        String confirmPassword = confirmpassField.getText();

        // Validate inputs
        if (!username.matches(userRegex)) {
            validationMessage.setText("Invalid username.");
            return;
        }
        if (!password.matches(passRegex)) {
            validationMessage.setText("Invalid password.");
            return;
        }
        if (!confirmPassword.equals(password)) {
            validationMessage.setText("Passwords do not match.");
            return;
        }
        if (!email.matches(emailRegex)) {
            validationMessage.setText("Invalid email.");
            return;
        }

        // Create a User object
        User newUser = new User(0, firstName, lastName, username, email, password);

        // Save the user to the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO();
            boolean isUserCreated = userDAO.createUser(connection, newUser);

            if (isUserCreated) {
                validationMessage.setText("Account created successfully!");
                // Redirect to login page
                backToLoginPage(actionEvent);
            } else {
                validationMessage.setText("Failed to create account. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            validationMessage.setText("An error occurred while saving the account.");
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
