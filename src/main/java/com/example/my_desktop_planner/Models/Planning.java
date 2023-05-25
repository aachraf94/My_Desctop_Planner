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
    public void addTachePlannifie(Tache tachePlannifie) {
        this.tachePlannifies.add(tachePlannifie);
    }
    public void addTacheUnscheduled(Tache tacheUnscheduled) {
        this.tacheUnscheduleds.add(tacheUnscheduled);
    }
    public void removeTachePlannifie(Tache tachePlannifie) {
        this.tachePlannifies.remove(tachePlannifie);
    }
    public void removeTacheUnscheduled(Tache tacheUnscheduled) {
        this.tacheUnscheduleds.remove(tacheUnscheduled);
    }

    public Planning(String nom, LocalDate dateDebut, LocalDate dateFin, Duration dureeMinCreneau, ArrayList<CreneauLibre> creneauLibres) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dureeMinCreneau = dureeMinCreneau;
        if (creneauLibres != null)
        {
            this.creneauLibres = new ArrayList<CreneauLibre>(creneauLibres);
        }
        else {
            this.creneauLibres = new ArrayList<CreneauLibre>();
        }
        this.creneauOccupes = new ArrayList<CreneauOccupe>();
        this.tachePlannifies = new ArrayList<Tache>();
        this.tacheUnscheduleds = new ArrayList<Tache>();
    }

    public Planning(Planning planning)
    {
        this.nom = new String(planning.getNom());
        this.dateDebut = planning.getDateDebut();
        this.dateFin = planning.getDateFin();
        this.dureeMinCreneau = planning.getDureeMinCreneau();
        if (planning.getCreneauLibres() != null)
        {
            this.creneauLibres = new ArrayList<CreneauLibre>(planning.getCreneauLibres());
        }
        else {
            this.creneauLibres = new ArrayList<CreneauLibre>();
        }
        this.creneauLibres = new ArrayList<CreneauLibre>(planning.getCreneauLibres());
        this.creneauOccupes = new ArrayList<CreneauOccupe>();
        this.tachePlannifies = new ArrayList<Tache>();
        this.tacheUnscheduleds = new ArrayList<Tache>();

    }

    public void ajouterCreneauLibre(CreneauLibre creneauLibre)
    {
        this.creneauLibres.add(creneauLibre);
    }


    /**************** Setters and getters ********************/

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

    public Duration getDureeMinCreneau() {
        return dureeMinCreneau;
    }

    public void setDureeMinCreneau(Duration dureeMinCreneau) {
        this.dureeMinCreneau = dureeMinCreneau;
    }

    public ArrayList<CreneauLibre> getCreneauLibres() {
        return creneauLibres;
    }

    public void setCreneauLibres(ArrayList<CreneauLibre> creneauLibres) {
        this.creneauLibres = creneauLibres;
    }

    public ArrayList<CreneauOccupe> getCreneauOccupes() {
        return creneauOccupes;
    }

    public void setCreneauOccupes(ArrayList<CreneauOccupe> creneauOccupes) {
        this.creneauOccupes = creneauOccupes;
    }
}