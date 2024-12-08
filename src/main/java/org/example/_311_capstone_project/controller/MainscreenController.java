package org.example._311_capstone_project.controller;
import database.DatabaseConnection;
import database.Movie;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuBar;


/**
 * @author paris
 * @version1.0
 * @date 11/16
 * working on a shell(base) code for MainScreen(mainly the search bar funcation), things will subject to change
 * as well as more func
 */


// Controller class for the main screen, will be expanded with main screen functionality
public class MainscreenController implements Initializable {

    @FXML
    private MenuItem aboutBtn;



    //may add/ change the titles to match with other class
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



    ObservableList<Movie> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnection DC = new DatabaseConnection();
        Connection con = DC.getConnection();

        //Once title are finalize will finish select command

        String movies = "SELECT movie_id, title, genre, release_date, rating, availability FROM movies";


        // try and catch for the SQL command

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(movies);
            while (rs.next()) {

                Integer movieId = rs.getInt("movie_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                Integer releaseYear = rs.getInt("release_date");
                Double rating = rs.getDouble("rating");
                Boolean availability = rs.getBoolean("availability");

                //populate
                list.add(new Movie(movieId, title, genre, releaseYear, rating, availability));

                MovieIDTitle.setCellValueFactory(new PropertyValueFactory<>("MovieID"));
                MovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                RateTitle.setCellValueFactory(new PropertyValueFactory<>("rating"));
                ReleaseYearTitle.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
                GenreTitle.setCellValueFactory(new PropertyValueFactory<>("genre"));

                MovieTable.setItems(list);

            }
        } catch (SQLException e) {
            Logger.getLogger(MainscreenController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        logout.setOnAction(this::Logout);
        History.setOnAction(this::gotoHistory);


    }

    @FXML
    void displayAboutInfo(ActionEvent event) {
        try {
            // Load the main screen scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/about.fxml"));
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



    public void Logout(ActionEvent event) {
        try {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 895, 650);

            // Get the current stage and close it
            Stage currentStage = (Stage) MenuBar.getScene().getWindow();
            currentStage.hide();  // Alternative to `close()`

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
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/_311_capstone_project/borrowed.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 895, 650);

            // Get the current stage and close it
            Stage currentStage = (Stage) MenuBar.getScene().getWindow();
            currentStage.hide();  // Alternative to `close()`

            // Open the new stage for login
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
