package org.example._311_capstone_project.controller;
import database.DatabaseConnection;
import database.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.MenuItem;
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



/**
 * @author paris
 * @version1.0
 * @date 11/16
 * working on a shell(base) code for MainScreen(mainly the search bar funcation), things will subject to change
 * as well as more func
 */


// Controller class for the main screen, will be expanded with main screen functionality
public class MainscreenController implements Initializable {


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



    ObservableList<Movie> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnection DC = new DatabaseConnection();
        Connection con = DC.getConnection();


        //Once title are finalize will finish select command


        String movie = "SELECT movieId,title,genre,releaseYear,rating From Movie";


        // try and catch for the SQL command

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(movie);
            while (rs.next()) {

                Integer movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                Integer releaseYear = rs.getInt("releaseYear");
                Double rating = rs.getDouble("rating");

                //populate
                list.add(new Movie(movieId, title, genre, releaseYear, rating));


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







    }
    @FXML
    public void Logout(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/_311_capstone_project/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
