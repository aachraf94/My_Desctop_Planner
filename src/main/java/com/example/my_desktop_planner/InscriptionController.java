package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.css.PseudoClass;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class InscriptionController {

    @FXML
    private Label erreurText;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField motDePasse;



    //    checked and updated
    @FXML
    void seConnecterButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SeConnecter.fxml"));
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

    //checked and updated
    @FXML
    void inscriptionButton(ActionEvent event) {

        String userPseudo = pseudo.getText();
        String userMotDePasse = motDePasse.getText();

        if (HelloApplication.myDesktopPlanner.isExist(userPseudo))
        {
            System.out.println("Pseudo existe deja!");
            inscriptionInvalide();
        }
        else
        {
            HelloApplication.utilisateurCourant = new Utilisateur(userPseudo,userMotDePasse);
            HelloApplication.myDesktopPlanner.addUser(HelloApplication.utilisateurCourant,userMotDePasse);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NvPlanning.fxml"));
            Scene scene = null;
            try
            {
                scene = new Scene(fxmlLoader.load());
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("Couldn't load NvPlanning.FXML file");
            }

            Button button = (Button)event.getSource();
            Stage stage = (Stage)button.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


    }

    //checked and updated
    @FXML
    void inscriptionInvalide()
    {
        erreurText.setText("Pseudo déja existe!");
    }


}

