package org.example._311_capstone_project.controller;

import database.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BorrowedController {

    @FXML
    private TableView<Movie> borrowedTable;

    @FXML
    private TableColumn<Movie, String> borrowedTitle;

    @FXML
    private TableColumn<Movie, String> borrowedGenre;

    @FXML
    private TableColumn<Movie, Integer> borrowedYear;

    private final ObservableList<Movie> borrowedMovies = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind table columns to Movie properties
        borrowedTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        borrowedGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        borrowedYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        // Set the observable list for the TableView
        borrowedTable.setItems(borrowedMovies);
    }

    public void addBorrowedMovie(Movie movie) {
        borrowedMovies.add(movie);
    }
}
