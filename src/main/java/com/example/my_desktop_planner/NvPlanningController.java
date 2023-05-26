package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.CreneauLibre;
import com.example.my_desktop_planner.Models.Planning;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NvPlanningController implements Initializable {

    private ArrayList<CreneauLibre> creneauLibres ;
    @FXML
    private TextField nomPlanning;
    @FXML
    private Label LibreTest;
    @FXML
    private ChoiceBox<String> duree;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private DatePicker creneauDatePicker;
    @FXML
    private CheckBox tousLesJours;
    @FXML
    private TextField creneauHeureDebut;
    @FXML
    private TextField creneauHeureFin;
    @FXML
    private Label ErreurLabel;
    @FXML
    private ListView<CreneauLibre> creneauLibreListView= new ListView<CreneauLibre>();


    private String[] dureeMin = {"15MIN", "30MIN", "1H", "1H 30MIN", "2H"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        duree.getItems().addAll(dureeMin);
        creneauLibres = new ArrayList<CreneauLibre>();
        tousLesJours.selectedProperty().addListener((observable, oldValue, newValue) -> {// Hide or show the periodicite TextField based on the CheckBox state
            creneauDatePicker.setVisible(!newValue);
        });
    }

    //checked and updated
    @FXML
    private void ajouterCreneau() {
        CreneauLibre creneauLibre = null;
        if (validateInputAjouterCreneau()) {

            if (!tousLesJours.isSelected()) {

                LocalDate selectedDate = creneauDatePicker.getValue();
                LocalTime timeDebut = LocalTime.parse(creneauHeureDebut.getText());
                LocalTime timeFin = LocalTime.parse(creneauHeureFin.getText());

                creneauLibre = new CreneauLibre(LocalDateTime.of(selectedDate, timeDebut), LocalDateTime.of(selectedDate, timeFin));
                creneauLibres.add(creneauLibre);
                creneauLibreListView.getItems().add(creneauLibre);
            } else {
                LocalDate date1 = dateDebut.getValue();
                LocalDate date2 = dateFin.getValue();

                LocalTime timeDebut = LocalTime.parse(creneauHeureDebut.getText());
                LocalTime timeFin = LocalTime.parse(creneauHeureFin.getText());

                for (LocalDate date = date1; !date.isAfter(date2); date = date.plusDays(1)) {
                    creneauLibre = new CreneauLibre(LocalDateTime.of(date, timeDebut), LocalDateTime.of(date, timeFin));
                    creneauLibres.add(creneauLibre);
                    creneauLibreListView.getItems().add(creneauLibre);
                }
            }

        }

    }


    //checked and updated
    @FXML
    private void ajouterPlanning(ActionEvent event) {
        System.out.println("\n\t"+HelloApplication.utilisateurCourant);
        if (validateInputAjouterPlanning()) {

            //Récupérer les données
            String nom = nomPlanning.getText();

            LocalDate debut = dateDebut.getValue();
            LocalDate fin = dateFin.getValue();
            Duration dure = parseDuree(duree.getValue());

            //update planning de utilisateur courant
            HelloApplication.utilisateurCourant.planning.setNom(nom);
            HelloApplication.utilisateurCourant.planning.setDateDebut(debut);
            HelloApplication.utilisateurCourant.planning.setDateFin(fin);
            HelloApplication.utilisateurCourant.planning.setDureeMinCreneau(dure);

            //set les creneaux libres
            HelloApplication.utilisateurCourant.planning.setCreneauLibres(creneauLibres);

            //changer fxml
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendar.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't load FXML file");
            }

            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }


    //Checked and upated
    private boolean validateInputAjouterPlanning() {
        ErreurLabel.setText("");

        String nom = nomPlanning.getText();
        LocalDate debut = dateDebut.getValue();
        LocalDate fin = dateFin.getValue();

        if (nom.isEmpty() || debut == null || fin == null || duree.getValue() == null) {
            ErreurLabel.setText("Veuillez remplir tous les champs obligatoires.");
            return false;
        } else if (fin.isBefore(LocalDate.now()) || debut.isBefore(LocalDate.now()) ) {
            ErreurLabel.setText("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.");
            return false;
        } else if (creneauLibres.size() <= 0 ) {
            ErreurLabel.setText("Veuillez au moins 1 Créneau libre");
            return false;
        }

        ErreurLabel.setText("");
        return true;
    }


    //Checked and upated
    private boolean validateInputAjouterCreneau() {
        ErreurLabel.setText("");

        LocalDate selectedDate = creneauDatePicker.getValue();
        String heureDebut = creneauHeureDebut.getText();
        String heureFin = creneauHeureFin.getText();

        if (heureDebut.isEmpty() || heureFin.isEmpty()) {
            ErreurLabel.setText("Veuillez sélectionner des heures valides.");
            return false;
        }

        if (!isValidTime(heureDebut) || !isValidTime(heureFin)) {
            ErreurLabel.setText("Veuillez entrer des heures valides au format HH:mm.");
            return false;
        }

        LocalTime timeDebut = LocalTime.parse(heureDebut);
        LocalTime timeFin = LocalTime.parse(heureFin);

        if (timeDebut.isAfter(timeFin)) {
            ErreurLabel.setText("Veuillez entrer une heure de début après l'heure de fin !");
            return false;
        }

        if (tousLesJours.isSelected()) {
            LocalDate debut = dateDebut.getValue();
            LocalDate fin = dateFin.getValue();

            if (debut == null || fin == null) {
                ErreurLabel.setText("Veuillez remplir toutes les dates de début et de fin.");
                return false;
            }

            if (fin.isBefore(LocalDate.now()) || debut.isBefore(LocalDate.now())) {
                ErreurLabel.setText("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.");
                return false;
            }
        } else {
            LocalDate currentDate = LocalDate.now();
            if (selectedDate.isBefore(currentDate)) {
                ErreurLabel.setText("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.");
                return false;
            }
        }

        return true;
    }

    //checked and updated
    private boolean isValidTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }



    //Checked and upated
    private Duration parseDuree(String dureeString) {
        switch (dureeString) {
            case "15MIN":
                return Duration.ofMinutes(15);
            case "30MIN":
                return Duration.ofMinutes(30);
            case "1H":
                return Duration.ofHours(1);
            case "1H 30MIN":
                return Duration.ofHours(1).plusMinutes(30);
            case "2H":
                return Duration.ofHours(2);
            default:
                return Duration.ZERO; // or handle invalid input as needed
        }
    }


}
