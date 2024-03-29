package com.example.my_desktop_planner;
import com.example.my_desktop_planner.Models.Tache;
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
import java.util.List;
import java.util.ResourceBundle;
import static com.example.my_desktop_planner.HelloApplication.myDesktopPlanner;
import static com.example.my_desktop_planner.HelloApplication.utilisateurCourant;

public class CalendarController implements Initializable {
    /************* To DO list *************/
    // régler unsch page
    // modifier la liste des taches après chaque modification dans la date
    // ajouter projet
    // badge
    //

    /************ agenda stuff ***********/
    ZonedDateTime dateFocus;
    ZonedDateTime today;
    @FXML
    Label date;
    private StackPane selectedDayRectangle;
    private LocalDate selected_day ;
    @FXML
    private Text year;
    @FXML
    private Text month;
    @FXML
    private FlowPane calendar;
    /**************************************/
    private List<Tache> taches;
    @FXML
    private ListView<Tache> listTache;

    /**************************************/
    @FXML
    private Label name;
    @FXML
    private BorderPane borderPane;
    private Stage stage;


    /****************** Methods *********************************************/

    //checked and updated
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        year.setText(String.valueOf(dateFocus.getYear())); // Initialize the year variable
        month.setText(String.valueOf(dateFocus.getMonth())); // Initialize the month variable
        drawCalendar();
        selected_day = LocalDate.now();
        date.setText(selected_day.toString());
        name.setText(utilisateurCourant.getPseudo());

        // set taches and viewlist
        taches = new ArrayList<>(utilisateurCourant.getPlanning().getTachePlannifies(LocalDate.now()));
        listTache.getItems().addAll(taches);


    }
    /***************** Calendar *****************************************************/
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

    // Checked and updated
    private void handleDayClick(StackPane clickedDayRectangle) {
        if (selectedDayRectangle != null) {
            Rectangle rec = (Rectangle) selectedDayRectangle.getChildren().get(0);
            rec.setFill(Color.WHITE);
        }

        if (selectedDayRectangle == clickedDayRectangle) {
            selectedDayRectangle = null;
        } else {
            Rectangle rec1 = (Rectangle) clickedDayRectangle.getChildren().get(0);
            rec1.setFill(Color.web("dfdfdf"));
            selectedDayRectangle = clickedDayRectangle;

            // Update selected_day with the clicked day
            int currentDate = Integer.parseInt(((Text) clickedDayRectangle.getChildren().get(1)).getText());
            selected_day = LocalDate.of(dateFocus.getYear(), dateFocus.getMonthValue(), currentDate);
            date.setText(selected_day.toString());
        }
        taches.clear();
        taches.addAll(utilisateurCourant.getPlanning().getTachePlannifies(selected_day));
        listTache.getItems().clear();
        listTache.getItems().addAll(taches);
    }

    /************************************************************************************/
    //checked and updated
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
        newStage.showAndWait();
//        updateListView();

    }


    //checked and updated
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
        taches.clear();
        taches.addAll(utilisateurCourant.planning.getTachePlannifies(selected_day));
        listTache.getItems().clear();
        listTache.getItems().addAll(taches);
    }


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



    @FXML
    void deconnercterButton(ActionEvent event) {
        myDesktopPlanner.getUtilisateurs().remove(utilisateurCourant);
        myDesktopPlanner.getUtilisateurs().put(utilisateurCourant,utilisateurCourant.getMdp());
        utilisateurCourant = null;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeConnecter.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        System.out.println("deconnection valide");
        currentStage.show();
    }



    public void updateListView()
    {
        // set taches and viewlist
        taches.clear();
        taches.addAll(utilisateurCourant.getPlanning().getTachePlannifies(LocalDate.now()));
        listTache.getItems().clear();
        listTache.getItems().addAll(taches);
    }






}

