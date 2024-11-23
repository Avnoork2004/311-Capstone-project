package org.example._311_capstone_project.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Main application to start the JavaFX application
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Loads login screen from FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/_311_capstone_project/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Sets the window title and displays the scene
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch();
    }
}
