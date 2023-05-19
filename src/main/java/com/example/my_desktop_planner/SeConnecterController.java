package com.example.my_desktop_planner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SeConnecterController {

    @FXML
    private Label erreurText;
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
    protected void connexionInvalid() {
        erreurText.setText("Pseudo ou mot de passe invalide!");
    }

}
