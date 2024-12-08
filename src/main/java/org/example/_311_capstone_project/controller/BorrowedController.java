package org.example._311_capstone_project.controller;

// Controller class for managing borrowed items, will be expanded with methods for borrow functionality


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class BorrowedController {

    @FXML
    private MenuItem historyBtn;

    @FXML
    void closeHistoryBtn(ActionEvent event) {
        try {
            // Load the main screen scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/mainscreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 895, 650);

            // Get the current stage using the event source
            Stage currentStage = (Stage) historyBtn.getParentPopup().getOwnerWindow();
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