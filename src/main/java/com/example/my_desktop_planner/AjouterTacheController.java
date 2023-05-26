package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.my_desktop_planner.HelloApplication.utilisateurCourant;

public class AjouterTacheController implements Initializable {


    @FXML
    private TextField NomTache;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField Duree;
    @FXML
    private DatePicker dateLim;
    @FXML
    private ColorPicker color;
    @FXML
    private CheckBox decomposable;
    @FXML
    private TextField periodicite;
    @FXML
    private CheckBox ajouterProjet;
    @FXML
    private TextField nomProjet;
    @FXML
    private CheckBox bloquee;

    @FXML
    private Label erreur;
    @FXML
    private ChoiceBox<Categorie> categorieChoiceBox;
    @FXML
    private ChoiceBox<Priorite> prioriteChoiceBox;
    @FXML
    private Button annulerlButton;
    @FXML
    private CheckBox AutoPlanification;
    @FXML
    private Button AjouterButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categorieChoiceBox.getItems().addAll(Categorie.STUDIES, Categorie.WORK, Categorie.HOBBY, Categorie.SPORT, Categorie.HEALTH, Categorie.OTHER);
        prioriteChoiceBox.getItems().addAll(Priorite.LOW, Priorite.MEDIUM, Priorite.HIGHT);

        decomposable.selectedProperty().addListener((observable, oldValue, newValue) -> {// Hide or show the periodicite TextField based on the CheckBox state
            periodicite.setVisible(!newValue);
        });

        // Add a listener to the ajouterProjet CheckBox
        ajouterProjet.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Show or hide the nomProjet TextField based on the CheckBox state
            nomProjet.setVisible(newValue);
        });


    }


    @FXML
    public void handleCancelButtonAction(ActionEvent event) {
        // Reset the UI elements or close the window without saving any changes
        clearFields();
        closeWindow();
    }

    private void clearFields() {
        // Clear the values of all the UI elements
        NomTache.clear();
        dateDebut.setValue(null);
        dateFin.setValue(null);
        Duree.clear();
        dateLim.setValue(null);
        color.setValue(Color.WHITE);
        decomposable.setSelected(false);
        periodicite.clear();
        ajouterProjet.setSelected(false);
        nomProjet.clear();
        bloquee.setSelected(false);
        erreur.setText("");
        categorieChoiceBox.getSelectionModel().clearSelection();
        prioriteChoiceBox.getSelectionModel().clearSelection();
    }

    private void closeWindow() {
        // Close the window or navigate to another view
        Stage stage = (Stage) annulerlButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void handleAjouterButtonAction(ActionEvent event) {
        if (validateInput()) {
            String nomTache = NomTache.getText();
            int dureeHeur = Integer.parseInt(Duree.getText());
            Duration duree = Duration.ofMinutes(dureeHeur);
            LocalDate dateDebutValue = dateDebut.getValue();
            LocalDate dateFinValue = dateFin.getValue();
            LocalDate dateLimValue = dateLim.getValue();
            Color colorValue = color.getValue();
            boolean isDecomposable = decomposable.isSelected();
            int periodiciteValue = Integer.parseInt(periodicite.getText());
            boolean isAjouterProjet = ajouterProjet.isSelected();
            String nomProjetValue = nomProjet.getText();
            boolean isBloquee = bloquee.isSelected();
            Categorie categorie = categorieChoiceBox.getValue();
            Priorite priorite = prioriteChoiceBox.getValue();

            if (isDecomposable) {
                TacheDecompo tacheDecompo = new TacheDecompo(nomTache, duree, priorite, dateLimValue, dateDebutValue, dateFinValue, categorie, colorValue, true, Etat.NOT_REALIZED, isBloquee, true, 0);
                utilisateurCourant.getPlanning().getTacheUnscheduleds().add(tacheDecompo);
                if (isAjouterProjet) {
                    ArrayList<Tache> taches = new ArrayList<>();
                    taches.add(tacheDecompo);
                    Projet projet = new Projet(nomProjetValue, "", taches);
                    // Add the project to the user's list of projects
                    utilisateurCourant.getPlanning().getProjets().add(projet);
                }
            } else {
                TacheSimple tacheSimple = new TacheSimple(nomTache, duree, priorite, dateLimValue, dateDebutValue, dateFinValue, categorie, colorValue, true, Etat.NOT_REALIZED, isBloquee, false, periodiciteValue);
                if (isAjouterProjet) {
                    ArrayList<Tache> taches = new ArrayList<>();
                    taches.add(tacheSimple);
                    Projet projet = new Projet(nomProjetValue, "", taches);
                    // Add the project to the user's list of projects
                    utilisateurCourant.getPlanning().getProjets().add(projet);
                }
            }
            clearFields();
            closeWindow();
        }
    }


    private boolean validateInput() {
        erreur.setText("");

        if (NomTache.getText().isEmpty() || prioriteChoiceBox.getValue() == null || categorieChoiceBox.getValue() == null || dateDebut.getValue() == null || dateFin.getValue() == null || (!decomposable.isSelected() && periodicite.getText().isEmpty()) || (ajouterProjet.isSelected() && nomProjet.getText().isEmpty()) || Duree.getText().isEmpty() || dateLim.getValue() == null) {
            erreur.setText("Veuillez remplir tous les champs obligatoires.");
        }

        if (dateDebut.getValue() != null && dateFin.getValue() != null && dateLim.getValue() != null) {
            LocalDate currentDate = LocalDate.now();
            if (dateDebut.getValue().isBefore(currentDate) || dateFin.getValue().isBefore(currentDate) || dateLim.getValue().isBefore(currentDate)) {
                erreur.setText(erreur.getText() + "\nVeuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.\n");
            }
        }

        try {
            int dureeValue = Integer.parseInt(Duree.getText());
            if (dureeValue <= 0) {
                erreur.setText(erreur.getText() + "La durée doit être un entier positif.\n");
            }
        } catch (NumberFormatException e) {
            erreur.setText(erreur.getText() + "La durée doit être un entier.\n");
        }

        if (!decomposable.isSelected() && !periodicite.getText().isEmpty()) {
            try {
                int periodiciteValue = Integer.parseInt(periodicite.getText());
                if (periodiciteValue <= 0) {
                    erreur.setText(erreur.getText() + "La périodicité doit être un entier positif.\n");
                }
            } catch (NumberFormatException e) {
                erreur.setText(erreur.getText() + "La périodicité doit être un entier.\n");
            }
        }

        // Display the error message if there are any validation errors
        if (erreur.getText().length() > 0) {
            return false; // Validation failed
        }

        return true; // Validation successful
    }




}
