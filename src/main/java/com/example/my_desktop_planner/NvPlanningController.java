package com.example.my_desktop_planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class NvPlanningController implements Initializable {

    @FXML
    private TextField nomPlanning;


    @FXML
    private ChoiceBox<String> myChoiceBox; // Add @FXML annotation here

    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;


    private String[] choices = {"15MIN", "30MIN", "1H", "1H 30MIN", "2H"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(choices);

    }




}
