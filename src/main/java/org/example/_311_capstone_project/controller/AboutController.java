package org.example._311_capstone_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class AboutController {

    @FXML
    private MenuBar MenuBar;

    @FXML
    private MenuItem aboutBtn;

    @FXML
    void closeAbout(ActionEvent event) {
        try {
            // Load the main screen scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/mainscreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 895, 650);

            // Get the current stage using the event source
            Stage currentStage = (Stage) aboutBtn.getParentPopup().getOwnerWindow();
            currentStage.close();

            // Open the new stage for the main screen
            Stage mainStage = new Stage();
            mainStage.setScene(scene);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
