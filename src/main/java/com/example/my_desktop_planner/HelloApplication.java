package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SeConnecter.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Desktop Planner");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        MyDesktopPlanner myDesktopPlanner= MyDesktopPlanner.getInstance();// declarer une fois pour assurer qu'il ya une seul instance de cette class
        //launch();
        Utilisateur utilisateur = new Utilisateur("user1","1234");
        Utilisateur utilisateur2 = new Utilisateur("user2","12454");
        Utilisateur utilisateur3 = new Utilisateur("user3","154352");

        /*try {
            myDesktopPlanner.addUser(utilisateur,"1234");
            myDesktopPlanner.addUser(utilisateur2,"12454");
            myDesktopPlanner.addUser(utilisateur3,"154352");
            myDesktopPlanner.saveUsersToFile();
            myDesktopPlanner.loadUsersFromFile();
        } catch (Exception e) {
            e.printStackTrace();
    }*/ try {
        myDesktopPlanner.loadUsersFromFile();
        myDesktopPlanner.removeUser(utilisateur2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<Utilisateur,String> entry : myDesktopPlanner.utilisateurs.entrySet()) {
            Utilisateur key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ": " + value);
        }

    }
}
