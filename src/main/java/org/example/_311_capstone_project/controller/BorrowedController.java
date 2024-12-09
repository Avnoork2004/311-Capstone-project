package org.example._311_capstone_project.controller;

// Controller class for managing borrowed items, will be expanded with methods for borrow functionality


import database.DatabaseConnection;
import database.Rental;
import database.RentalDAO;
import database.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;

public class BorrowedController {

    @FXML
    private MenuItem historyBtn;

    @FXML
    private TableView<Rental> borrowedTable;

    @FXML
    private TableColumn<Rental, Integer> rentalIdColumn;

    @FXML
    private TableColumn<Rental, Integer> movieIdColumn;

    @FXML
    private TableColumn<Rental, String> rentalDateColumn;

    @FXML
    private TableColumn<Rental, String> returnDateColumn;

    @FXML
    private Button returnButton;

    private ObservableList<Rental> borrowedList;

    public void initialize() {
        borrowedList = FXCollections.observableArrayList();
        setupTableColumns();
        loadBorrowedItems();
    }

    private void setupTableColumns() {
        rentalIdColumn.setCellValueFactory(new PropertyValueFactory<>("rentalId"));
        movieIdColumn.setCellValueFactory(new PropertyValueFactory<>("movieId"));
        rentalDateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    private void loadBorrowedItems() {
        RentalDAO rentalDAO = new RentalDAO();
        String username = UserSession.getInstance(null, null).getUserName();

        try (Connection connection = DatabaseConnection.getConnection()) {
            borrowedList.setAll(rentalDAO.getRentalsByUserId(connection, Integer.parseInt(username))); // Fetch rentals using username
            borrowedTable.setItems(borrowedList); // Populate the table
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Invalid username format", Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (Exception e) {
            showAlert("Error", "Failed to load borrowed items", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    @FXML
    public void handleReturn(ActionEvent event) {
        Rental selectedRental = borrowedTable.getSelectionModel().getSelectedItem();
        if (selectedRental == null) {
            showAlert("Warning", "Please select a rental to return", Alert.AlertType.WARNING);
            return;
        }

        RentalDAO rentalDAO = new RentalDAO();
        try (Connection connection = DatabaseConnection.getConnection()) {
            boolean isReturned = rentalDAO.handleReturn(connection, selectedRental.getRentalId(), selectedRental.getMovieId());
            if (isReturned) {
                borrowedList.remove(selectedRental);
                showAlert("Success", "Movie returned successfully", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to return the movie", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

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