package com.example.my_desktop_planner.Models;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class Planning implements Serializable {
    public Duration dureeMinCreneau;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private ArrayList<CreneauLibre> creneauLibres;
    private ArrayList<CreneauOccupe> creneauOccupes;
    private ArrayList<Tache> tachePlannifies;
    private ArrayList<Tache> tacheUnscheduleds;

    /********* Le constructeur **********/
    public Planning()
    {
        nom = new String();
        creneauLibres = new ArrayList<CreneauLibre>();
        creneauOccupes = new ArrayList<CreneauOccupe>();
        tachePlannifies = new ArrayList<Tache>();
        tacheUnscheduleds = new ArrayList<Tache>();
    }


    /********* Methods ***************************************/




    /**************** Setters and getters ********************/

    public Duration getDureeMinCreneau() {
        return dureeMinCreneau;
    }

    public void setDureeMinCreneau(Duration dureeMinCreneau) {
        this.dureeMinCreneau = dureeMinCreneau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public ArrayList<CreneauLibre> getCreneauLibres() {
        return creneauLibres;
    }

    public void setCreneauLibres(ArrayList<CreneauLibre> creneauLibres) {
        this.creneauLibres.clear();
        this.creneauLibres.addAll(creneauLibres);
    }

    public ArrayList<CreneauOccupe> getCreneauOccupes() {
        return creneauOccupes;
    }

    public void setCreneauOccupes(ArrayList<CreneauOccupe> creneauOccupes) {
        this.creneauOccupes.clear();
        this.creneauOccupes.addAll(creneauOccupes);
    }

    public ArrayList<Tache> getTachePlannifies() {
        return tachePlannifies;
    }
    // getTache plannifies pour Le LocalDate
    public ArrayList<Tache> getTachePlannifies(LocalDate j) {

        ArrayList<Tache> tachesJJMM = new ArrayList<Tache>();

        for (Tache tache: this.tachePlannifies ) {
            if (tache.getDateDebut().equals(j))
            {
                tachesJJMM.add(tache);
            }
        }
        return tachesJJMM;
    }

    public void setTachePlannifies(ArrayList<Tache> tachePlannifies) {
        this.tachePlannifies.clear();
        this.tachePlannifies.addAll(tachePlannifies);
    }

    public ArrayList<Tache> getTacheUnscheduleds() {
        return tacheUnscheduleds;
    }

    public void setTacheUnscheduleds(ArrayList<Tache> tacheUnscheduleds) {
        this.tacheUnscheduleds.clear();
        this.tacheUnscheduleds.addAll(tacheUnscheduleds);
    }


}