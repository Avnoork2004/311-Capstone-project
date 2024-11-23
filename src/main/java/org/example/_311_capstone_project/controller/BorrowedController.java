package org.example._311_capstone_project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class BorrowedController {

    @FXML
    private ListView<String> borrowedItemsList;

    public void initialize() {
        borrowedItemsList.getItems().addAll("Item 1", "Item 2", "Item 3");
    }
}