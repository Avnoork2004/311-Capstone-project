package org.example._311_capstone_project.controller;

import database.DatabaseConnection;
import database.Movie;
import database.MovieDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainscreenController implements Initializable {

    @FXML
    private MenuItem aboutBtn;

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
    private TextField Search;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuBar MenuBar;

    @FXML
    private MenuItem History;

    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        loadMoviesFromDatabase();
        setupMenuActions();

        Search.setOnAction(event -> searchMovies());
    }

    private void setupTableColumns() {
        // Map table columns to Movie class properties
        MovieIDTitle.setCellValueFactory(new PropertyValueFactory<>("movieId"));
        MovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        GenreTitle.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ReleaseYearTitle.setCellValueFactory(cellData -> {
            java.sql.Date releaseDate = cellData.getValue().getReleaseDate();
            Integer year = (releaseDate != null) ? releaseDate.toLocalDate().getYear() : null;
            return new SimpleObjectProperty<>(year); // Use SimpleObjectProperty for Integer values
        });
        RateTitle.setCellValueFactory(new PropertyValueFactory<>("rating"));
    }

    private void loadMoviesFromDatabase() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            MovieDAO movieDAO = new MovieDAO();
            List<Movie> movies = movieDAO.getAllMovies(connection);
            if (movies.isEmpty()) {
                System.out.println("No movies found in the database.");
            } else {
                System.out.println("Movies fetched from database: " + movies.size());
                movies.forEach(movie -> System.out.println(movie.getTitle())); // Debug movie titles
            }
            movieList.clear();
            movieList.addAll(movies);
            MovieTable.setItems(movieList);
        } else {
            System.out.println("Failed to load movies: Database connection is null.");
        }
    }


    private void setupMenuActions() {
        logout.setOnAction(this::logout);
        History.setOnAction(this::gotoHistory);
        aboutBtn.setOnAction(this::displayAboutInfo);
    }

    @FXML
    void displayAboutInfo(ActionEvent event) {
        try {
            // Load the about scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/about.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 895, 650);
            scene.getStylesheets().add(getClass().getResource("/org/example/_311_capstone_project/about.css").toExternalForm());


            // Get the current stage and close it
            Stage currentStage = (Stage) aboutBtn.getParentPopup().getOwnerWindow();
            currentStage.close();

            // Open the new stage for the about page
            Stage aboutStage = new Stage();
            aboutStage.setScene(scene);
            aboutStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        try {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 895, 650);

            // Get the current stage and close it
            Stage currentStage = (Stage) MenuBar.getScene().getWindow();
            currentStage.close();

            // Open the new stage for login
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
            // Load the history scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/borrowed.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 895, 650);

            // Get the current stage and close it
            Stage currentStage = (Stage) MenuBar.getScene().getWindow();
            currentStage.close();

            // Open the new stage for history
            Stage historyStage = new Stage();
            historyStage.setScene(scene);
            historyStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void searchMovies() {
        String query = Search.getText().trim();
        if (query.isEmpty()) {
            // If the search query is empty, show all movies again
            loadMoviesFromDatabase();
            return;
        }

        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            MovieDAO movieDAO = new MovieDAO();
            List<Movie> filteredMovies = movieDAO.searchMoviesByTitle(connection, query);

            movieList.clear();
            movieList.addAll(filteredMovies);
            MovieTable.setItems(movieList);

            if (filteredMovies.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText(null);
                alert.setContentText("No movies found for the search query: " + query);
                alert.showAndWait();
            }
        } else {
            System.out.println("Failed to connect to the database for searching movies.");
        }
    }
}