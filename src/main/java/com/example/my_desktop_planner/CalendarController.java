package com.example.my_desktop_planner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;
    private StackPane selectedDayRectangle;
    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
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
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 2;
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
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
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

                        stackPane.setOnMouseClicked(event -> handleDayClick(stackPane,currentDate));


                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setFill(Color.web("005B8F"));
                        Text date = (Text) stackPane.getChildren().get(1);
                        date.setFill(Color.web("000000"));
                    }
                }

                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void handleDayClick(StackPane clickedDayRectangle, int dayOfMonth) {
        if (selectedDayRectangle != null) {
            Rectangle rec=(Rectangle)selectedDayRectangle.getChildren().get(0);
            rec.setFill(Color.TRANSPARENT);//
        }

        if (selectedDayRectangle == clickedDayRectangle) {
            selectedDayRectangle = null;
        } else {
            Rectangle rec1=(Rectangle)clickedDayRectangle.getChildren().get(0);
            rec1.setFill(Color.web("605B8F"));
            selectedDayRectangle = clickedDayRectangle;
        }

        return ;
    }

}
