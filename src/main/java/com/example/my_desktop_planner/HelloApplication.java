package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
/*** java.nio Chikour ***/
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloApplication extends Application {

    public static MyDesktopPlanner myDesktopPlanner;
    public static Utilisateur utilisateurCourant;
    private static Stage stage;
    public static final String DIRECTORY_PATH = "src/main/resources/com/example/my_desktop_planner";
    public static final String FILE_NAME = "myDesktopPlanner.dat";
    public static final Path FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;

        // Load the initial scene (SeConnecter.fxml)
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SeConnecter.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        if (Files.exists(FILE_PATH)) {
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(FILE_PATH))) {
                myDesktopPlanner = (MyDesktopPlanner) in.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
            myDesktopPlanner = new MyDesktopPlanner();
            createFile();
        }

        // Configure the stage
        stage.setTitle("My Desktop Planner");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        myDesktopPlanner.sauvegarder();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    private static void createFile() {
        try {
            if (!Files.exists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }
            Files.createFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
