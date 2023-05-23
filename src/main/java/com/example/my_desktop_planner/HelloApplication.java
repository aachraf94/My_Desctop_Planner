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
<<<<<<< HEAD
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NvPlanning.fxml"));
=======
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Calendar.fxml"));
>>>>>>> 4b4e19141d6fefcad1f880f57bdfbc2e3a0dbe8b
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Desktop Planner");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        MyDesktopPlanner myDesktopPlanner= MyDesktopPlanner.getInstance();
        launch();

    }
}
