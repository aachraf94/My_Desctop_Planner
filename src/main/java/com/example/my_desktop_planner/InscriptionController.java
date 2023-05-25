package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
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


public class InscriptionController {
    MyDesktopPlanner desktopPlanner = MyDesktopPlanner.getInstance();


    @FXML
    private Label erreurText;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField motDePasse;


    @FXML
    void seConnecterButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SeConnecter.fxml"));
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

    @FXML
    void inscriptionButton(ActionEvent event) {
        if (desktopPlanner.getUtilisateurs().containsValue(motDePasse.getText())) {
            inscriptionInvalide();
        } else {

            desktopPlanner.loadUsersFromFile();
            Utilisateur utilisateur = new Utilisateur(pseudo.getText(), motDePasse.getText());
            utilisateur.afficher();
            if (desktopPlanner.addUser(utilisateur, motDePasse.toString())){
                System.out.println("Utilisateur ajouté");
                desktopPlanner.saveUsersToFile();
                CalendarController.utilisateur_courant = utilisateur;
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NvPlanning.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Couldn't load FXML file");
                }
                stage.setScene(scene);
            }else {
                System.out.println("Utilisateur non ajouté");
                inscriptionInvalide();
            }



        }
    }

    @FXML
    void inscriptionInvalide() {
        erreurText.setText("Pseudo déja existe!");
    }

    public void receive_data(String data) {
        System.out.println(data);
    }

    public void send_data(String data) {
        System.out.println(data);
    }

}

