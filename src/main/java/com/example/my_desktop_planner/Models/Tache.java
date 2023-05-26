package com.example.my_desktop_planner.Models;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;


abstract public class Tache implements Serializable, Comparable<Tache> {
    private String nom;
    private Duration dure;
    private Priorite priorite;
    private LocalDate dateLim;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Categorie categorie;
    private SerializableColor color;
    private boolean unscheduled;
    private Etat etat;
    private boolean bloque;
    private boolean decomposable;


    //plannifier manuelle ou auto

    //re-planifier (string dureeSupp, String nvDeadline)          les deux


    public Tache(String nom, Duration dure, Priorite priorite, LocalDate dateLim, LocalDate dateDebut, LocalDate dateFin, Categorie categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable) {
        this.nom = nom;
        this.dure = dure;
        this.priorite = priorite;
        this.dateLim = dateLim;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.categorie = categorie;
        this.color = new SerializableColor(color) ;
        this.unscheduled = unscheduled;
        this.etat = etat;
        this.bloque = bloque;
        this.decomposable = decomposable;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Duration getDure() {
        return dure;
    }

    public void setDure(Duration dure) {
        this.dure = dure;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public LocalDate getDateLim() {
        return dateLim;
    }

    public void setDateLim(LocalDate dateLim) {
        this.dateLim = dateLim;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Color getColor() {
        return color.getFXColor();
    }

    public void setColor(Color color) {
        this.color = new SerializableColor(color);
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

    @Override
    public String toString() {
        return "" + nom +
                "" + priorite +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", categorie=" + categorie +
                ", etat=" + etat +
                '}';
    }

    @Override
    public int compareTo(Tache autreTache) {
        return this.dateDebut.compareTo(autreTache.dateDebut);

    }
}
