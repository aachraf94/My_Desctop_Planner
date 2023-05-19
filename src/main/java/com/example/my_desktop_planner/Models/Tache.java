package com.example.my_desktop_planner.Models;

abstract public class Tache {
    private String nom;
    private String dure; //peut etre type date, donc a revoir
    private Priorite priorite;
    private String dateLim ; // a revoir
    private String dateDebut; // a revoir
    private String dateFin; // a revoir
    private String categorie;
    private String coleur;
    private boolean unscheduled;
    private Etat etat;


    //plannifier manuelle ou auto

    //re-planifier (string dureeSupp, String nvDeadline)          les deux



}
