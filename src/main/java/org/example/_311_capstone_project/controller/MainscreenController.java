package org.example._311_capstone_project.controller;
import database.DatabaseConnection;
import database.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private TableView<?> MovieTable;


    @FXML
    private TableColumn<?, String> MovieTitle;


    @FXML
    private TableColumn<?, String> RateTitle;


    @FXML
    private TableColumn<?, String> RentTitle;


    @FXML
    private TextField Search;

    // will change the placeholdername later

    //ObservableList<placeholder> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnection DC = new DatabaseConnection();
        Connection con = DC.getConnection();


        //Once title are finalize will finish select command


        String placeholder = "SELECT ";


        // try and catch for the SQL command


        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(placeholder);
            while (rs.next()) {
                //get the titles for the database


                // calls list.add(*titles*)


                MovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                RateTitle.setCellValueFactory(new PropertyValueFactory<>("rating"));
                RentTitle.setCellValueFactory(new PropertyValueFactory<>("rating"));


                // MovieTable.setItems(*list.add(*titles*)*)
            }
        } catch (SQLException e) {
            Logger.getLogger(MainscreenController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }






    }




}
