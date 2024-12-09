package org.example._311_capstone_project.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloApplication extends Application {
    private Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Debugging log to check if the resource is found
            System.out.println("Loading SplashScreen.fxml...");

            // Load and show SplashScreen.fxml
            URL splashScreenUrl = getClass().getResource("/org/example/_311_capstone_project/SplashScreen.fxml");
            if (splashScreenUrl == null) {
                System.err.println("Failed to find SplashScreen.fxml");
            } else {
                Parent root = FXMLLoader.load(splashScreenUrl);
                Scene scene = new Scene(root, 900, 600);
                scene.getStylesheets().add(getClass().getResource("/css/lightTheme.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            }

            // Simulate a delay for the splash screen, if needed
            Thread.sleep(2000); // Adjust the delay as necessary

            // After the splash screen, load the login screen
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/_311_capstone_project/login.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load(), 895, 650); // Set login scene size

            // Set the main application scene (login screen)
            stage.setScene(loginScene);

            // Optionally, you can show the startup notification here as well
            showStartupNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**  private void showScene1() {
        try{ Parent root = FXMLLoader.load(getClass().getResource("/org/example/_311_capstone_project/SplashScreen.fxml"));
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.show();

            showStartupNotification();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }*/


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
