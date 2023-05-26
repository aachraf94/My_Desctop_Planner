package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {

    public static MyDesktopPlanner myDesktopPlanner;
    public static Utilisateur utilisateurCourant;
    private static Stage stage;


    @Override
    public void start(Stage stage) throws IOException {

        HelloApplication.stage = stage;


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendar.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AjouterEnsTache.fxml"));
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

        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("myDesktopPlanner.dat"))));
            try {
                myDesktopPlanner = (MyDesktopPlanner) in.readObject();
                in.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            myDesktopPlanner = new MyDesktopPlanner();
        } catch (IOException e) {
            e.printStackTrace();
        }


        stage.setTitle("My Desktop Planner");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop()
    {
        myDesktopPlanner.sauvegarder();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {return stage;}



}

