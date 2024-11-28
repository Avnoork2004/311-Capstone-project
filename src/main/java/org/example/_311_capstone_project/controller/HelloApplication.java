package org.example._311_capstone_project.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Loads login screen from FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/_311_capstone_project/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 895, 650);

        // Sets the window title and displays the scene
        stage.setTitle("MediaVault");
        stage.setScene(scene);
        stage.show();

        // Show startup notification
        showStartupNotification();
    }

    // Method to show a system tray notification
    private void showStartupNotification() {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported!");
            return;
        }

        // Create a system tray instance
        SystemTray tray = SystemTray.getSystemTray();

        // Use an image for the tray icon (replace with a valid icon path)
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/browsingMovies.png"));
        TrayIcon trayIcon = new TrayIcon(image, "MediaVault");
        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);

            // Display a notification
            trayIcon.displayMessage(
                    "MediaVault Started",
                    "MediaVault is now running!",
                    TrayIcon.MessageType.INFO
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

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch();
    }
}
