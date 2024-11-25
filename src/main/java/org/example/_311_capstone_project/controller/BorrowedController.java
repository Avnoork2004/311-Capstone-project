package org.example._311_capstone_project.controller;

import database.DatabaseConnection;
import database.Movie;
import database.MovieDAO;
import database.Rental;
import database.RentalDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.List;

public class BorrowedController {

    @FXML
    private TableView<Movie> borrowedTable;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private TableColumn<Movie, String> genreColumn;

    @FXML
    private TableColumn<Movie, String> releaseDateColumn;

    @FXML
    private TableColumn<Movie, Double> ratingColumn;

    @FXML
    private TableColumn<Movie, Boolean> availabilityColumn;

    private ObservableList<Movie> borrowedMovies;

    public void initialize() {
        // Initialize table columns
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        borrowedMovies = FXCollections.observableArrayList();
        borrowedTable.setItems(borrowedMovies);

        // Load borrowed movies
        loadBorrowedMovies();
    }

    private void loadBorrowedMovies() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            return;
        }

        try {
            // Fetch rentals for the current user
            RentalDAO rentalDAO = new RentalDAO();
            UserSession currentUser = UserSession.getInstance(null, null); // Replace with actual session retrieval
            List<Rental> rentals = rentalDAO.getRentalsByUserId(connection, currentUser.getUserName());

            // Fetch movie details for each rental
            MovieDAO movieDAO = new MovieDAO();
            for (Rental rental : rentals) {
                Movie movie = movieDAO.getMovieById(connection, rental.getMovieId());
                if (movie != null) {
                    borrowedMovies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
