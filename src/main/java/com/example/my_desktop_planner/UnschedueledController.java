package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.Tache;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static com.example.my_desktop_planner.HelloApplication.utilisateurCourant;

public class UnschedueledController implements Initializable {
    @FXML
    private ListView<Tache> tacheListView;
    private ArrayList<Tache> taches;
    @FXML
    private DatePicker datePicker;
    @FXML
    TextField hDebut;
    @FXML
    TextField hFin;
    @FXML
    private Label tacheSelectione;
    @FXML
    private Label erreurLabel;


    /*********** Initialize ******************/
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        taches = new ArrayList<Tache>(utilisateurCourant.getPlanning().getTacheUnscheduleds());
        tacheListView.getItems().clear();
        tacheListView.getItems().addAll(taches);
    }

    /********* Methods **********************/
    @FXML
    private void autoPlanifierButton() {
    }

    @FXML
    private void planifierManuButton() {
    }

    @FXML
    private void annulerButton() {
    }

    @FXML
    private void autoPlanifierToutButton() {
    }
    @FXML
    private void tacheListViewItemSelected() {
        // Handle the event when a task is selected in the ListView
        Tache selectedTache = tacheListView.getSelectionModel().getSelectedItem();
        if (selectedTache != null) {
            tacheSelectione.setText(selectedTache.getNom());
        } else {
            tacheSelectione.setText("");
        }
    }


    private boolean isValidTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}