package com.example.my_desktop_planner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

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
        launch();
    }
}
