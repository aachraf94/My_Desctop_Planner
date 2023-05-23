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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


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
    void inscriptionButton(ActionEvent event)
    {
        if (desktopPlanner.getUtilisateurs().containsValue(motDePasse.getText())){
            inscriptionInvalide();
        }
        else {

            desktopPlanner.loadUsersFromFile();
            Utilisateur utilisateur = new Utilisateur(pseudo.getText(), motDePasse.getText());
            utilisateur.afficher();
            desktopPlanner.addUser(utilisateur, motDePasse.toString());
            desktopPlanner.saveUsersToFile();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SeConnecter.fxml"));
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
//@FXML
//private void sendData(MouseEvent event) {
//    // Step 1
//    Utilisateur u = new Utilisateur(pseudo.getText(),motDePasse.getText());
//    Node node = (Node) event.getSource();
//    Stage stage = (Stage) node.getScene().getWindow();
//    stage.close();
//    try {
//        // Step 2
//        FXMLLoader loader = FXMLLoader.load(getClass().getClassLoader().getResource("SeConnecter.fxml"));
//        // Step 3
//        SeConnecterController controller = new SeConnecterController();
//        controller.setUtilisateur(u);
//        // Step 4
//        loader.setController(controller);
//        // Step 5
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException e) {
//        System.err.println(String.format("Error: %s", e.getMessage()));
//    }
//}



    @FXML
    void inscriptionInvalide()
    {
        erreurText.setText("Pseudo déja existe!");
    }

    public void receive_data(String data){
        System.out.println(data);}
    public void send_data(String data){
        System.out.println(data);}

}

