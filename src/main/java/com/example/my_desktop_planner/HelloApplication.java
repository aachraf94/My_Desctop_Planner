package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendar.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SeConnecter.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Desktop Planner");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            e.consume();
            logout(stage);
        });
    }

    public void logout(Stage stage) {
        MyDesktopPlanner desktopPlanner = MyDesktopPlanner.getInstance();
        desktopPlanner.loadUsersFromFile();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("All unsaved changes will be lost");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("logout successful");
            desktopPlanner.saveUsersToFile();
            stage.close();
        }
    }


        public static void main (String[]args){
            MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();
            launch();

        }
    }

