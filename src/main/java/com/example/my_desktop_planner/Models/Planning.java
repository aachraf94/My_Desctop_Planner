package com.example.my_desktop_planner.Models;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Planning {
//    private Calendar calendirer;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    public Duration dureeMinCreneau;
    private ArrayList<CreneauLibre> creneauLibres;
    private ArrayList<CreneauOccupe> creneauOccupes;
    private ArrayList<Tache> tachePlannifies;
    private ArrayList<Tache> tacheUnscheduleds;

    public ArrayList<Tache> getTachePlannifies() {
        return tachePlannifies;
    }

    public void setTachePlannifies(ArrayList<Tache> tachePlannifies) {
        this.tachePlannifies = tachePlannifies;
    }

    public ArrayList<Tache> getTacheUnscheduleds() {
        return tacheUnscheduleds;
    }

    public void setTacheUnscheduleds(ArrayList<Tache> tacheUnscheduleds) {
        this.tacheUnscheduleds = tacheUnscheduleds;
    }
/*** a completer ****/
//    public void plannifierTacheManuellemt (Creneau creneau,TacheSimple tacheSimple)
//    {
//        if (this.creneauLibres.contains(creneau))
//        {
//
//
//
//        }else
//        {
//            System.out.println("\nLe créneau n'est pas libre!");
//        }
//    }


    //plannifier      -->  décomposer le créneau libre selon la durée de la tache
    // replanifier  -->  décomposer le créneau libre selon la durée de la tache


}