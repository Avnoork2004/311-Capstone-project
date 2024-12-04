package org.example._311_capstone_project.controller;

import database.DatabaseConnection;
import database.Movie;
import database.MovieDAO;
import database.Rental;
import database.RentalDAO;
import javafx.beans.property.SimpleStringProperty;
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
    private TableColumn<Rental, String> dateBorrowed;

    @FXML
    private TableColumn<Rental, String> dateReturn;

    private ObservableList<Movie> borrowedMovies;
    private ObservableList<Rental> rentalDetails;

    public void initialize() {
        // Initialize table columns
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateBorrowed.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        dateReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        borrowedMovies = FXCollections.observableArrayList();
        rentalDetails = FXCollections.observableArrayList();

        borrowedTable.setItems(borrowedMovies);

        // Load borrowed movies and rental details
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
            List<Rental> rentals = rentalDAO.getRentalsByUserId(connection, Integer.parseInt(currentUser.getUserName()));

            // Fetch movie details for each rental
            MovieDAO movieDAO = new MovieDAO();
            for (Rental rental : rentals) {
                Movie movie = movieDAO.getMovieById(connection, rental.getMovieId());
                if (movie != null) {
                    borrowedMovies.add(movie);
                    rentalDetails.add(rental);
                }
            }

            // Bind the rental details to the corresponding columns
            dateBorrowed.setCellValueFactory(data -> {
                Rental rental = rentalDetails.stream()
                        .filter(r -> r.getMovieId() == data.getValue().getMovieId())
                        .findFirst()
                        .orElse(null);
                return rental != null ? new SimpleStringProperty(rental.getRentalDate()) : null;
            });

            dateReturn.setCellValueFactory(data -> {
                Rental rental = rentalDetails.stream()
                        .filter(r -> r.getMovieId() == data.getValue().getMovieId())
                        .findFirst()
                        .orElse(null);
                return rental != null ? new SimpleStringProperty(rental.getReturnDate()) : null;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
