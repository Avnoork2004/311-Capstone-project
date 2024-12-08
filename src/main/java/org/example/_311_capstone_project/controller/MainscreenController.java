package org.example._311_capstone_project.controller;

import database.DatabaseConnection;
import database.Movie;
import database.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class MainscreenController implements Initializable {

    @FXML
    private TableColumn<Movie, String> GenreTitle;

    @FXML
    private TableColumn<Movie, Integer> MovieIDTitle;

    @FXML
    private TableView<Movie> MovieTable;

    @FXML
    private TableColumn<Movie, String> MovieTitle;

    @FXML
    private TableColumn<Movie, Double> RateTitle;

    @FXML
    private TableColumn<Movie, Integer> ReleaseYearTitle;

    @FXML
    private TableColumn<Movie, Void> ActionTitle;

    @FXML
    private TextField Search;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuBar MenuBar;

    @FXML
    private MenuItem History;

    ObservableList<Movie> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        loadMovies();
        addButtonToTable();
    }

    private void setupTableColumns() {
        // Configure other columns
        MovieIDTitle.setCellValueFactory(new PropertyValueFactory<>("movieId"));
        MovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        GenreTitle.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ReleaseYearTitle.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        RateTitle.setCellValueFactory(new PropertyValueFactory<>("rating"));
    }

    private void loadMovies() {
        list.clear();
        try (Connection connection = DatabaseConnection.getConnection()) {
            MovieDAO movieDAO = new MovieDAO();
            List<Movie> movies = movieDAO.getAllMovies(connection);
            list.addAll(movies);
            MovieTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addButtonToTable() {
        // Create a cell factory to generate Rent buttons
        Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>> cellFactory = param -> new TableCell<>() {
            private final Button rentButton = new Button("Rent");

            {
                rentButton.setOnAction((ActionEvent event) -> {
                    Movie movie = getTableView().getItems().get(getIndex());
                    if (movie != null) {
                        copyToBorrowedTable(movie);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(rentButton);
                }
            }
        };

        // Assign the cell factory to the ActionTitle column
        ActionTitle.setCellFactory(cellFactory);
    }

    private void copyToBorrowedTable(Movie movie) {
        try {
            // Load BorrowedController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/borrowed.fxml"));
            Parent root = loader.load();

            BorrowedController borrowedController = loader.getController();
            borrowedController.addBorrowedMovie(movie);

            // Navigate to Borrowed screen
            Stage stage = (Stage) MovieTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);

            Stage currentStage = (Stage) MenuBar.getScene().getWindow();
            currentStage.hide();

            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoHistory(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/borrowed.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);

            Stage currentStage = (Stage) MenuBar.getScene().getWindow();
            currentStage.hide();

            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
