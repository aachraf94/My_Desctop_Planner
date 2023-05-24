package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.UserNotFoundException;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class SeConnecterController {

    private Utilisateur utilisateur;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label erreurText;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField motDePasse;

    @FXML
    void nvCompteButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Inscription.fxml"));
        scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Button button = (Button) event.getSource();
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void seConnecterButton(ActionEvent event) throws UserNotFoundException, IOException {
        MyDesktopPlanner desktopPlanner = MyDesktopPlanner.getInstance();
        desktopPlanner.loadUsersFromFile();
        //System.out.println(!desktopPlanner.getUtilisateurs().containsKey(new Utilisateur(pseudo.getText(),motDePasse.getText())));
        if (!desktopPlanner.getUtilisateurs().containsKey(new Utilisateur(pseudo.getText(), motDePasse.getText()))) {
            connexionInvalid();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendar.fxml"));
            CalendarController calendarController = new CalendarController();

            HashMap<Utilisateur, String> map = desktopPlanner.getUtilisateurs();

            Set<Utilisateur> utilisateurSet = map.keySet();
            for (Utilisateur user : utilisateurSet) {
                String pseudo1 = pseudo.getText();
                String mdp = motDePasse.getText();                                                /// la partie hadi makhroba
                if (user.equals(new Utilisateur(pseudo1, mdp)) && user.getMdp().equals(mdp)) {
                    user.afficher();
                    calendarController.setUtilisateur(user);
                    //calendarController.setText(pseudo1);
                    break;
                } else {
                    connexionInvalid();

                }
            }


            root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);


        }
    }


    public void connexionInvalid() {
        erreurText.setText("Pseudo ou mot de passe invalide!");
    }

    void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }


}