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

    private ArrayList<CreneauLibre> creneauLibres = new ArrayList<CreneauLibre>();
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

        tousLesJours.selectedProperty().addListener((observable, oldValue, newValue) -> {// Hide or show the periodicite TextField based on the CheckBox state
            creneauDatePicker.setVisible(!newValue);
        });
    }

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
                CalendarController.utilisateur_courant.ajouterCreneauLibre(creneauLibre);
            } else {
                LocalDate date1 = dateDebut.getValue();
                LocalDate date2 = dateFin.getValue();

                LocalTime timeDebut = LocalTime.parse(creneauHeureDebut.getText());
                LocalTime timeFin = LocalTime.parse(creneauHeureFin.getText());

                for (LocalDate date = date1; !date.isAfter(date2); date = date.plusDays(1)) {
                    creneauLibre = new CreneauLibre(LocalDateTime.of(date, timeDebut), LocalDateTime.of(date, timeFin));
                    creneauLibres.add(creneauLibre);
                    CalendarController.utilisateur_courant.ajouterCreneauLibre(creneauLibre);
                    creneauLibreListView.getItems().add(creneauLibre);
                }
            }

        }

    }

    @FXML
    private void ajouterPlanning(ActionEvent event) {
        System.out.println("\n\t"+CalendarController.utilisateur_courant);
        if (validateInputAjouterPlanning()) {
            String nom = nomPlanning.getText();
            LocalDate debut = dateDebut.getValue();
            LocalDate fin = dateFin.getValue();
            Planning planning = new Planning(nom, debut, fin, parseDuree(duree.getValue()), creneauLibres);
            CalendarController.utilisateur_courant.setPlanning(planning);

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

    private boolean validateInputAjouterPlanning() {
        ErreurLabel.setText("");

        String nom = nomPlanning.getText();
        LocalDate debut = dateDebut.getValue();
        LocalDate fin = dateFin.getValue();

        if (nom.isEmpty() || debut == null || fin == null || duree.getValue() == null) {
            ErreurLabel.setText("Veuillez remplir tous les champs obligatoires.");
            return false;
        } else if (fin.isBefore(LocalDate.now())) {
            ErreurLabel.setText("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.");
            return false;
        }

        ErreurLabel.setText("");
        return true;
    }


    private boolean validateInputAjouterCreneau() {
        ErreurLabel.setText("");

        LocalDate selectedDate = creneauDatePicker.getValue();
        String heureDebut = creneauHeureDebut.getText();
        String heureFin = creneauHeureFin.getText();

        if (heureDebut.isEmpty() || heureFin.isEmpty()) {
            ErreurLabel.setText("Veuillez sélectionner des heures valides.");
            return false;
        }

        try {
            LocalTime timeDebut = LocalTime.parse(heureDebut);
            LocalTime timeFin = LocalTime.parse(heureFin);

            // Perform further validation if needed

        } catch (DateTimeParseException e) {
            ErreurLabel.setText("Veuillez entrer des heures valides au format HH:mm.");
            return false;
        }

        if (tousLesJours.isSelected()) {
            LocalDate debut = dateDebut.getValue();
            LocalDate fin = dateFin.getValue();

            if (debut == null || fin == null) {
                ErreurLabel.setText("Veuillez remplir toutes les dates de début et de fin.");
                return false;
            } else if (fin.isBefore(LocalDate.now()) || debut.isBefore(LocalDate.now())) {
                ErreurLabel.setText("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.");
                return false;
            }

            ErreurLabel.setText("");
            return true;
        } else {
            LocalDate currentDate = LocalDate.now();
            if (selectedDate.isBefore(currentDate)) {
                ErreurLabel.setText("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.");
                return false;
            }
            return true;
        }
    }


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
