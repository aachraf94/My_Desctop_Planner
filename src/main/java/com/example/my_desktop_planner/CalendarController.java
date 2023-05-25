package com.example.my_desktop_planner;


import com.example.my_desktop_planner.Models.*;

import com.example.my_desktop_planner.Models.MyDesktopPlanner;
import com.example.my_desktop_planner.Models.Planning;
import com.example.my_desktop_planner.Models.Tache;
import com.example.my_desktop_planner.Models.TacheSimple;
import com.example.my_desktop_planner.Models.Utilisateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    public static Utilisateur utilisateur_courant;
    ZonedDateTime dateFocus;
    ZonedDateTime today;
    private StackPane selectedDayRectangle;
    private LocalDate selected_day ;
    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @FXML
    private ListView<Tache> listTache;
    @FXML
    private Label id = new Label();
    @FXML
    private Button LogOutButton ;
    @FXML
    private BorderPane borderPane;
    private Stage stage;
    private String motDePasse;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        year.setText(String.valueOf(dateFocus.getYear())); // Initialize the year variable
        month.setText(String.valueOf(dateFocus.getMonth())); // Initialize the month variable
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar(){
       // System.out.println(dateFocus.getMonthValue() + " " + dateFocus.getYear());

        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        //Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.web("FFFFFF"));
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        date.setFont(Font.font("Arial",(int) Math.round(rectangleWidth/3)));
                        stackPane.getChildren().add(date);

                        stackPane.setOnMouseClicked(event -> {handleDayClick(stackPane);
                            selected_day = (ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), currentDate,0,0,0,0,dateFocus.getZone())).toLocalDate();
                            updateDayTasks(selected_day);
                        });


                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setFill(Color.web("777777"));
                        Text date = (Text) stackPane.getChildren().get(1);
                        date.setFill(Color.web("FFFFFF"));
                    }
                }

                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void handleDayClick(StackPane clickedDayRectangle) {
        if (selectedDayRectangle != null) {
            Rectangle rec=(Rectangle)selectedDayRectangle.getChildren().get(0);
            rec.setFill(Color.WHITE);//
        }

        if (selectedDayRectangle == clickedDayRectangle) {
            selectedDayRectangle = null;
        } else {
            Rectangle rec1=(Rectangle)clickedDayRectangle.getChildren().get(0);
            rec1.setFill(Color.web("dfdfdf"));
            selectedDayRectangle = clickedDayRectangle;
        }

    }

    public void receive_data(String data){
        System.out.println(data);}

    public Utilisateur getUtilisateur() {
        return utilisateur_courant;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur_courant = utilisateur;
    }

    @FXML
    void ajouterTacheButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AjouterTache.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        Stage newStage = new Stage();
        newStage.setTitle("Ajouter Tâche");
        newStage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        newStage.setScene(scene);
        newStage.show();

    }


    @FXML
    void ajouterEnsTacheButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AjouterEnsTache.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Stage newStage = new Stage();
        newStage.setTitle("Ajouter ensemble de Tâches");
        newStage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        newStage.setScene(scene);
        newStage.show();

    }
    private void updateDayTasks(LocalDate date) {
        listTache.getItems().clear();
        ArrayList<Tache> tasks = new ArrayList<Tache>();
        Tache tache1 = TacheSimple.generateRandomTask();
        Tache tache2 = TacheSimple.generateRandomTask();
        Tache tache3 = TacheSimple.generateRandomTask();
        utilisateur_courant.getTasks(date);
        listTache.getItems().clear();
        listTache.getItems().add(TacheSimple.generateRandomTask());
        listTache.getItems().add(TacheSimple.generateRandomTask());
        listTache.getItems().add(TacheSimple.generateRandomTask());

    }
    void displayname(){
        System.out.println(utilisateur_courant);
    }


    public void logout(ActionEvent event) {
        MyDesktopPlanner desktopPlanner = MyDesktopPlanner.getInstance();
        desktopPlanner.loadUsersFromFile();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("All unsaved changes will be lost");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) borderPane.getScene().getWindow();
            System.out.println("logout successful");
            desktopPlanner.sauvgrader(this.utilisateur_courant);
            stage.close();
        }}

    @FXML
    void unschedueledButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Unschedueld.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Stage newStage = new Stage();
        newStage.setTitle("Ajouter ensemble de Tâches");
        newStage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon2.png"))));
        newStage.setScene(scene);
        newStage.show();

    }





    @Override
    public String toString() {
        return "CalendarController{" +
                "dateFocus=" + dateFocus +
                ", today=" + today +
                ", selectedDayRectangle=" + selectedDayRectangle +
                ", selected_day=" + selected_day +
                ", year=" + year +
                ", month=" + month +
                ", calendar=" + calendar +
                ", listTache=" + listTache +
                ", id=" + id +
                ", LogOutButton=" + LogOutButton +
                ", borderPane=" + borderPane +
                ", stage=" + stage +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}

