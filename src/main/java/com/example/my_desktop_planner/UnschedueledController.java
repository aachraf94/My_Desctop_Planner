package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Models.Tache;
import com.example.my_desktop_planner.Models.Utilisateur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class UnschedueledController implements Initializable {
    @FXML
    ListView<Tache> listTache;
    Utilisateur utilisateur_courant = CalendarController.utilisateur_courant;


//    public Tache planifierTache() {
//        return ;
//    }
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
    listTache.getItems().addAll(CalendarController.utilisateur_courant.planning.getTacheUnscheduleds());
    listTache.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tache>() {
        @Override
        public void changed(ObservableValue<? extends Tache> observableValue, Tache tache, Tache t1) {

        }
    });
    }

}
