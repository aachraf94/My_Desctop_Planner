package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.Tache;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    TextField  hFin;
    @FXML
    private Label tacheSelected;
    private Tache tache ;
    @FXML
    private Label erreurLabel;
    @FXML
    private Button annulerlButton;


    /*********** Initialize ******************/
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        taches = new ArrayList<Tache>(utilisateurCourant.getPlanning().getTacheUnscheduleds());
        tacheListView.getItems().clear();
        tacheListView.getItems().addAll(taches);
        erreurLabel.setText("");

    }

    /********* Methods **********************/
    @FXML
    private void autoPlanifierButton() {
    }

    @FXML
    private void planifierManuButton() {
    }

    @FXML
    private void autoPlanifierToutButton() {
    }
    //a revoir
    @FXML
    private void tacheListViewItemSelected() {
        // Handle the event when a task is selected in the ListView
        tache = tacheListView.getSelectionModel().getSelectedItem();
        if (tache != null) {
            tacheSelected.setText(tache.getNom());
        } else {
            tacheSelected.setText("");
        }
    }

    @FXML
    public void handleCancelButtonAction(ActionEvent event) {
        // Reset the UI elements or close the window without saving any changes
        clearFields();
        closeWindow();
    }
    private void closeWindow() {
        // Close the window or navigate to another view
        Stage stage = (Stage) annulerlButton.getScene().getWindow();
        stage.close();
    }

    private boolean isValidTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void clearFields() {
        tache = null;
        datePicker.setValue(null);
        hDebut.clear();
        hFin.clear();
        erreurLabel.setText("");
    }

}