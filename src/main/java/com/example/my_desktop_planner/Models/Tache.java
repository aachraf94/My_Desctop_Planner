package com.example.my_desktop_planner.Models;
import java.text.DateFormat;

abstract public class Tache {
    private String nom;
    private DateFormat dure;
    private Priorite priorite;
    private String dateLim ; // a revoir
    private String dateDebut; // a revoir
    private String dateFin; // a revoir
    private String categorie;
    private Color color;
    private boolean unscheduled;
    private Etat etat;
    private boolean bloque;
    private boolean decomposable;



    //plannifier manuelle ou auto

    //re-planifier (string dureeSupp, String nvDeadline)          les deux


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public DateFormat getDure() {
        return dure;
    }

    public void setDure(DateFormat dure) {
        this.dure = dure;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public String getDateLim() {
        return dateLim;
    }

    public void setDateLim(String dateLim) {
        this.dateLim = dateLim;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isUnscheduled() {
        return unscheduled;
    }

    public void setUnscheduled(boolean unscheduled) {
        this.unscheduled = unscheduled;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public boolean isBloque() {
        return bloque;
    }

    public void setBloque(boolean bloque) {
        this.bloque = bloque;
    }

    public boolean isDecomposable() {
        return decomposable;
    }

    public void setDecomposable(boolean decomposable) {
        this.decomposable = decomposable;
    }
}
