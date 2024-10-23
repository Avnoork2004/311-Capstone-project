package org.example._311_capstone_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
                ArrayList<String> Movie = new ArrayList<>(
                        Arrays.asList("John Wick, R","Jurassic Park, PG-13","Shrek, E","Maxxxine,R","Deadpool 3, R")


                );

    @FXML
    private ListView<String> MovieList;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button Find;


    @FXML
    public void findMovie(javafx.event.ActionEvent event) {
        MovieList.getItems().clear();


        if (SearchBar.getText().isEmpty()) {
            MovieList.getItems().addAll(Movie); // Show all movies if no search term
        } else {
            MovieList.getItems().addAll(search(SearchBar.getText(),Movie));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MovieList.getItems().addAll(Movie);

    }

    private List<String> search(String searchText,List<String> movieList) {

        List<String> filteredsearchText = Arrays.asList(searchText.trim().toLowerCase().split(" "));
        return movieList.stream().filter(input ->{
            return filteredsearchText.stream().allMatch(Movie -> input.toLowerCase().contains(Movie.toLowerCase()));
        }).collect(Collectors.toList());
    }


}

