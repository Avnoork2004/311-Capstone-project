package org.example._311_capstone_project.controller;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the splash screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/SplashScreen.fxml"));
        Parent splashRoot = loader.load();
        Scene splashScene = new Scene(splashRoot, 895, 650); // Set width and height

        // Set the application icon
        Image appIcon = new Image(getClass().getResource("/images/splash.png").toExternalForm());
        primaryStage.getIcons().add(appIcon);

        // Set the stage for the splash screen
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Media Vault");
        primaryStage.show();

        // Show startup notification
        showStartupNotification();

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

                // Set the same application icon for the login screen
                primaryStage.getIcons().add(appIcon);

                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Login - Media Vault");
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        fadeTransition.play();
    }

    // Method to show a system tray notification
    private void showStartupNotification() {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported!");
            return;
        }

        // Create a system tray instance
        SystemTray tray = SystemTray.getSystemTray();

        // Use an image for the tray icon
        java.awt.Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/browsingMovies.png"));
        TrayIcon trayIcon = new TrayIcon(image, "MediaVault");
        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);

            // Display a notification
            trayIcon.displayMessage(
                    "MediaVault Started",
                    "MediaVault is now running!",
                    MessageType.INFO
            );
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    // Clean up the system tray on application exit
    @Override
    public void stop() {
        SystemTray tray = SystemTray.getSystemTray();
        for (TrayIcon trayIcon : tray.getTrayIcons()) {
            tray.remove(trayIcon);
        }
        System.out.println("Application exited, tray icon removed.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}