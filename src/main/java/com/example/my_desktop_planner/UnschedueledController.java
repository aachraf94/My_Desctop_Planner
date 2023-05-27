package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.CreneauLibre;
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
    private Tache tacheSelected;
    @FXML
    private ListView<CreneauLibre> creneauLibreListView;
    private ArrayList<CreneauLibre> creneauLibres;
    private CreneauLibre creneauLibreSelected;
    @FXML
    private DatePicker datePicker;
    @FXML
    TextField hDebut;
    @FXML
    TextField  hFin;
    private Tache tache ;
    @FXML
    private Label erreurLabel;
    @FXML
    private Button annulerlButton;
    @FXML
    private Label labelTacheSelected;
    @FXML
    private Label labelCreneauSelected;



    /*********** Initialize ******************/
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        //initialiser les liste view
        taches = new ArrayList<Tache>(utilisateurCourant.getPlanning().getTacheUnscheduleds());
        creneauLibres = new ArrayList<CreneauLibre>(utilisateurCourant.getPlanning().getCreneauLibres());

        //
        tacheListView.getItems().clear();
        tacheListView.getItems().addAll(taches);
        creneauLibreListView.getItems().clear();
        creneauLibreListView.getItems().addAll(creneauLibres);

        erreurLabel.setText("");
        labelTacheSelected.setText("");
        labelCreneauSelected.setText("");


    }

    /********* Methods **********************/
    @FXML
    private void autoPlanifierButton() {

        boolean planife = false;

        for (CreneauLibre creneauLibre1 :utilisateurCourant.getPlanning().getCreneauLibres() )  {
            if (utilisateurCourant.planning.planifier(creneauLibre1,tacheSelected) && !planife)
            {
                planife = true;
                break;
            }
            if (planife)
            {
                break;
            }
        }

        if (planife)
        {
            erreurLabel.setText("La tache est bien plannifie");
            updatedListView();
        }
        else
        {
            erreurLabel.setText("La tache n'est pas planifie!");
        }
    }

//    @FXML
//    private void planifierManuButton() {
//    }

    @FXML
    private void autoPlanifierToutButton() {
    }
    //a revoir
    @FXML
    private void creneauLibreListViewItemSelected() {
        // Handle the event when a task is selected in the ListView
        creneauLibreSelected = creneauLibreListView.getSelectionModel().getSelectedItem();
        if (creneauLibreSelected != null) {
            labelCreneauSelected.setText(creneauLibreSelected.toString());
        } else {
            labelCreneauSelected.setText("");
        }
    }

    @FXML
    private void tacheListViewItemSelected() {
        // Handle the event when a task is selected in the ListView
        tacheSelected = tacheListView.getSelectionModel().getSelectedItem();
        if (tacheSelected != null) {
            labelTacheSelected.setText(tacheSelected.getNom());
        } else {
            labelTacheSelected.setText("");
        }
    }

    @FXML
    public void handleCancelButtonAction(ActionEvent event) {
        // Reset the UI elements or close the window without saving any changes

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


    @FXML
    private void planifierManuButton() {

        if (creneauLibreSelected != null && tacheSelected!=null )
        {
            if(utilisateurCourant.planning.planifier(creneauLibreSelected, tacheSelected))
            {
                updatedListView();
            }
            else
            {
                erreurLabel.setText("Selectionnez autre creneau svp!");
            }
        }
        else
        {
            labelCreneauSelected.setText("Selectionnez creneau et tache!");
        }

    }


    private void updatedListView()
    {
        //initialiser les liste view
        taches = new ArrayList<Tache>(utilisateurCourant.getPlanning().getTacheUnscheduleds());
        creneauLibres = new ArrayList<CreneauLibre>(utilisateurCourant.getPlanning().getCreneauLibres());

        //
        tacheListView.getItems().clear();
        tacheListView.getItems().addAll(taches);
        creneauLibreListView.getItems().clear();
        creneauLibreListView.getItems().addAll(creneauLibres);

        erreurLabel.setText("");
        labelTacheSelected.setText("");
        labelCreneauSelected.setText("");
    }







}