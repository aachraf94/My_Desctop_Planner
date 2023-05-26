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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.my_desktop_planner.HelloApplication.utilisateurCourant;

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
    private Button AjouterButton;
    /*****************************  View list  ****************/
    @FXML
    private ListView<Tache> listViewTache;
    private ArrayList<Tache> arrayListViewTache ;
    /************************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ajouter les options de choix pour la ChoiceBox Categorie
        categorieChoiceBox.getItems().addAll(Categorie.values());

        // Ajouter les options de choix pour la ChoiceBox Priorite
        prioriteChoiceBox.getItems().addAll(Priorite.values());

        // Ajouter un écouteur de changement d'état pour la CheckBox decomposable
        decomposable.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Afficher ou masquer le champ de texte periodicite en fonction de l'état de la CheckBox
            periodicite.setVisible(!newValue);
        });
        // Ajouter un écouteur de changement d'état pour la ListView listViewTache
        arrayListViewTache = new ArrayList<Tache>();
        listViewTache.getItems().addAll(arrayListViewTache);
    }

/**********************************************************************************************************/
    @FXML
    public void handleCancelButtonAction(ActionEvent event) {
        // Reset the UI elements or close the window without saving any changes
        listViewTache.getItems().clear();
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
    public void handleAjouterTacheButtonAction(ActionEvent event) {
        if (validateInput()) {
            // Récupérer les valeurs des éléments de l'interface utilisateur
            String nomTache = NomTache.getText();
            int dureeValue = Integer.parseInt(Duree.getText());
            LocalDate dateLimValue = dateLim.getValue();
            LocalDate dateDebutValue = dateDebut.getValue();
            LocalDate dateFinValue = dateFin.getValue();
            Categorie categorie = categorieChoiceBox.getValue();
            Color colorValue = color.getValue();
            boolean isDecomposable = decomposable.isSelected();
            String periodiciteValue = periodicite.getText();
            int periodiciteInt = Integer.parseInt(periodiciteValue);
            boolean isBloquee = bloquee.isSelected();
            Priorite priorite = prioriteChoiceBox.getValue();

            if (isDecomposable) {
                // Créer une instance de TacheDecompo avec les valeurs saisies
                TacheDecompo tacheDecompo = new TacheDecompo(
                        nomTache, Duration.ofHours(dureeValue), priorite,
                        dateLimValue, dateDebutValue, dateFinValue,categorie,
                        colorValue, true, Etat.NOT_REALIZED, isBloquee, true,0
                );

                // Ajouter la représentation de la tâche à l'ArrayList et à la ListView
                arrayListViewTache.add(tacheDecompo);
                listViewTache.getItems().add(tacheDecompo);
            } else {
                TacheSimple tacheSimple = new TacheSimple(
                        nomTache, Duration.ofHours(dureeValue), priorite,
                        dateLimValue,dateDebutValue, dateDebutValue, categorie,
                        colorValue, true, Etat.NOT_REALIZED, isBloquee, false, periodiciteInt
                );
                // Ajouter la représentation de la tâche à l'ArrayList et à la ListView
                arrayListViewTache.add(tacheSimple);
                listViewTache.getItems().add(tacheSimple);
            }
            clearFields();
        }
    }

    @FXML
    public void handleAjouterToutButton(ActionEvent event) {
        utilisateurCourant.getPlanning().getTacheUnscheduleds().addAll(arrayListViewTache);
        arrayListViewTache.clear();
        listViewTache.getItems().clear();
        clearFields();
        closeWindow();
    }

    private boolean validateInput() {
        StringBuilder errorMessage = new StringBuilder();

        // Validate the 'NomTache' field
        if (NomTache.getText().isEmpty()) {
            errorMessage.append("Nom de tâche est requis.\n");
        }

        // Validate the 'prioriteChoiceBox' field
        if (prioriteChoiceBox.getValue() == null) {
            errorMessage.append("Priorité est requise.\n");
        }

        // Validate the 'categorieChoiceBox' field
        if (categorieChoiceBox.getValue() == null) {
            errorMessage.append("Catégorie est requise.\n");
        }

        // Validate the 'dateDebut' field
        LocalDate dateDebutValue = dateDebut.getValue();
        if (dateDebutValue == null || dateDebutValue.isBefore(LocalDate.now())) {
            errorMessage.append("Veuillez sélectionner une date de début valide.\n");
        }

        // Validate the 'dateFin' field
        LocalDate dateFinValue = dateFin.getValue();
        if (dateFinValue == null || dateFinValue.isBefore(LocalDate.now())) {
            errorMessage.append("Veuillez sélectionner une date de fin valide.\n");
        }

        // Validate the 'Duree' field
        String dureeText = Duree.getText();
        if (dureeText.isEmpty()) {
            errorMessage.append("Durée est requise.\n");
        } else {
            try {
                int dureeValue = Integer.parseInt(dureeText);
                if (dureeValue <= 0) {
                    errorMessage.append("Durée doit être un entier positif.\n");
                }
            } catch (NumberFormatException e) {
                errorMessage.append("Durée doit être un entier.\n");
            }
        }

        // Validate the 'dateLim' field
        LocalDate dateLimValue = dateLim.getValue();
        if (dateLimValue == null || dateLimValue.isBefore(LocalDate.now())) {
            errorMessage.append("Veuillez sélectionner une date limite valide.\n");
        }

        // Validate the 'periodicite' field if 'decomposable' is selected
        if (!decomposable.isSelected() && periodicite.getText().isEmpty()) {
            errorMessage.append("Périodicité est requise si la tâche est non décomposable.\n");
        } else if (!periodicite.getText().isEmpty()) {
            try {
                int periodiciteValue = Integer.parseInt(periodicite.getText());
                if (periodiciteValue <= 0) {
                    errorMessage.append("Périodicité doit être un entier positif.\n");
                }
            } catch (NumberFormatException e) {
                errorMessage.append("Périodicité doit être un entier.\n");
            }
        }

        // Display the error message if there are any validation errors
        if (errorMessage.length() > 0) {
            erreur.setText(errorMessage.toString());
            return false; // Validation failed
        }

        return true;
    }

}
