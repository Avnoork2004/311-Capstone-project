package org.example._311_capstone_project.controller;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the splash screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/SplashScreen.fxml"));
        Parent splashRoot = loader.load();
        Scene splashScene = new Scene(splashRoot, 895, 650); // Set width and height

        // Set the stage for the splash screen
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Media Vault");
        primaryStage.show();

        // Fade out the splash screen and load the login screen
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), splashRoot);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> {
            try {
                // Load the login screen
                FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/login.fxml"));
                Parent loginRoot = loginLoader.load();
                Scene loginScene = new Scene(loginRoot);

                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Login - Media Vault");
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        fadeTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
