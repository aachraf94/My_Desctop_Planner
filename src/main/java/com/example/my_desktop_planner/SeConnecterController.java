package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.UserNotFoundException;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeConnecterController {
    MyDesktopPlanner desktopPlanner = MyDesktopPlanner.getInstance();
    private Utilisateur utilisateur ;

    @FXML
    private Label erreurText;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField motDePasse;
    @FXML
    void nvCompteButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Inscription.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(fxmlLoader.load());
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Button button = (Button)event.getSource();
        Stage stage = (Stage)button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void seConnecterButton(ActionEvent event) throws UserNotFoundException {
        desktopPlanner.loadUsersFromFile();
        if (!desktopPlanner.getUtilisateurs().containsKey(new Utilisateur(pseudo.getText(),motDePasse.getText()))){
            connexionInvalid();
        }
        else {
            CalendarController calendarController = new CalendarController();
            HashMap<Utilisateur, String> map = desktopPlanner.getUtilisateurs();
            for (Map.Entry<Utilisateur, String> entry : map.entrySet()){
                if ((entry.getKey().getPseudo().equals(pseudo.getText())) && (entry.getValue().equals(motDePasse.getText()))){
                    utilisateur = entry.getKey();
                    calendarController.setUtilisateur(utilisateur);
                }
            }
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendar.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = null;
            try
            {
                scene = new Scene(fxmlLoader.load());
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("Couldn't load FXML file");
            }

            stage.setScene(scene);


        }
    }

    @FXML
    protected void connexionInvalid() {
        erreurText.setText("Pseudo ou mot de passe invalide!");
    }

    void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }


}
