package com.example.my_desktop_planner.Models;

import java.time.LocalDate;
import java.util.*;

public class Planning {
    Calendar calendirer;
    ArrayList<Tache> taches;
    LocalDate dateDebut;
    LocalDate dateFin;
    ArrayList<Creneau> creneaus;

    void plannier(Tache t, Creneau c) {
        //if (c.estLibre()){
        //t.setCreneau(c);
        //taches.add(t);
        //}
    }
    //void replanifier (Planning p) {
    //   if (p.valideDecomp()){
    //    taches.add(p.taches);}


    //Pour que l’application puisse effectuer la
    //planification, l’utilisateur spécifie, en premier, ses créneaux libres durant lesquels les tâches peuvent
    //être programmées


    //plannifier      -->  décomposer le créneau libre selon la durée de la tache
    // replanifier  -->  décomposer le créneau libre selon la durée de la tache
    void ajouterCreneauLibre(Creneau c) {
        creneaus.add(c);
    }

    void supprimerCreneauLibre(Creneau c) {
        creneaus.remove(c);
    }
    //definir les crzeneau libre d'une facons globale
}
