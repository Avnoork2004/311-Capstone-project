package org.example._311_capstone_project.controller;

import database.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BorrowedController {

    @FXML
    private TableView<Movie> borrowedTable;

    @FXML
    private TableColumn<Movie, String> MovieTitleColumn;

    @FXML
    private TableColumn<Movie, String> GenreColumn;

    @FXML
    private TableColumn<Movie, String> RentalDateColumn;

    private static ObservableList<Movie> borrowedMovies = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        borrowedTable.setItems(borrowedMovies);
    }

    private void setupTableColumns() {
        MovieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        GenreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        RentalDateColumn.setCellValueFactory(movie -> {
            return new javafx.beans.property.SimpleStringProperty(java.time.LocalDate.now().toString());
        });
    }

    public static void addBorrowedMovie(Movie movie) {
        borrowedMovies.add(movie);
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    public void closeHistoryBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/mainscreen.fxml"));
            Parent root = loader.load();

            // Get the stage from the MenuItem's event source
            MenuItem menuItem = (MenuItem) event.getSource();
            Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
            Stage stage = (Stage) scene.getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
