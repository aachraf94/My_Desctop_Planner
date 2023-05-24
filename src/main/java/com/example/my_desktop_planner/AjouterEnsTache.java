package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterEnsTache implements Initializable {
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
    private TextField tempdPlanification;
    @FXML
    private Button AjouterButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categorieChoiceBox.getItems().addAll(Categorie.STUDIES, Categorie.WORK, Categorie.HOBBY,Categorie.SPORT, Categorie.HEALTH, Categorie.OTHER);
        prioriteChoiceBox.getItems().addAll( Priorite.LOW , Priorite.MEDIUM, Priorite.HIGHT );

        decomposable.selectedProperty().addListener((observable, oldValue, newValue) -> {// Hide or show the periodicite TextField based on the CheckBox state
            periodicite.setVisible(!newValue);
        });



        AutoPlanification.selectedProperty().addListener((observable, oldValue, newValue) -> {
            tempdPlanification.setVisible(!newValue);
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
            // Get the values from the UI elements
            String nomTache = NomTache.getText();
            String duree = Duree.getText();
            LocalDate dateLimValue = dateLim.getValue();
            LocalDate dateDebutValue = dateDebut.getValue();
            LocalDate dateFinValue = dateFin.getValue();
            Categorie categorie = categorieChoiceBox.getValue();
            Color colorValue = color.getValue();
            boolean isDecomposable = decomposable.isSelected();
            boolean isUnscheduled = AutoPlanification.isSelected();
            String periodiciteValue = periodicite.getText();
            boolean isBloquee = bloquee.isSelected();
            Priorite priorite = prioriteChoiceBox.getValue();
            if (isDecomposable) {
                //Tache Tache = new TacheSimple(nomTache, dateDebutValue, dateFinValue, duree, dateLimValue, colorValue, isDecomposable, periodiciteValue, isBloquee, categorie, priorite);
                periodiciteValue = "0";
            }
            // Perform the necessary operations with the values (e.g., save to a database, update model, etc.)

            // Clear the fields and close the window
            clearFields();
            closeWindow();
        }
    }

    private boolean validateInput() {
        StringBuilder errorMessage = new StringBuilder();
        int errorCount = 0;

        // Validate the 'NomTache' field
        if (NomTache.getText().isEmpty()) {
            errorMessage.append("Nom de tâche est requis.\n");
            errorCount++;
        }

        // Validate the 'prioriteChoiceBox' field
        if (prioriteChoiceBox.getValue() == null) {
            errorMessage.append("Priorité est requise.\n");
            errorCount++;
        }

        // Validate the 'categorieChoiceBox' field
        if (categorieChoiceBox.getValue() == null) {
            errorMessage.append("Catégorie est requise.\n");
            errorCount++;
        }

        // Validate the 'dateDebut' field
        if (dateDebut.getValue() == null) {
            errorMessage.append("Date de début est requise.\n");
            errorCount++;
        } else if (dateDebut.getValue().isBefore(LocalDate.now())) {
            errorMessage.append("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.\n");
            errorCount++;
        }

        // Validate the 'dateFin' field
        if (dateFin.getValue() == null) {
            errorMessage.append("Date de fin est requise.\n");
            errorCount++;
        } else if (dateFin.getValue().isBefore(LocalDate.now())) {
            errorMessage.append("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.\n");
            errorCount++;
        }

        // Validate the 'Duree' field
        if (Duree.getText().isEmpty()) {
            errorMessage.append("Durée est requise.\n");
            errorCount++;
        } else {
            try {
                int dureeValue = Integer.parseInt(Duree.getText());
                if (dureeValue <= 0) {
                    errorMessage.append("Durée doit être un entier positif.\n");
                    errorCount++;
                }
            } catch (NumberFormatException e) {
                errorMessage.append("Durée doit être un entier.\n");
                errorCount++;
            }
        }

        // Validate the 'dateLim' field
        if (dateLim.getValue() == null) {
            errorMessage.append("Date limite est requise.\n");
            errorCount++;
        } else if (dateLim.getValue().isBefore(LocalDate.now())) {
            errorMessage.append("Veuillez sélectionner une date à partir d'aujourd'hui ou les jours suivants.\n");
            errorCount++;
        }

        // Validate the 'periodicite' field if 'decomposable' is selected
        if (!decomposable.isSelected() && periodicite.getText().isEmpty()) {
            errorMessage.append("Périodicité est requise si la tâche est non décomposable.\n");
            errorCount++;
        }

        // Display the error message if there are any validation errors
        if (errorMessage.length() > 0) {
            erreur.setText(errorMessage.toString());
//            double minHeight = super.stage.getMinHeight();
//            double newHeight = minHeight + (errorCount * 20);
//            super.stage.setMinHeight(newHeight);

            return false; // Validation failed
        }

        return true;
    }




}
