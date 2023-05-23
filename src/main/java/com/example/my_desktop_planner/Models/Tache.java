package com.example.my_desktop_planner.Models;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

abstract public class Tache implements Serializable {
    private String nom;
    private Duration dure;
    private Priorite priorite;
    private LocalDate dateLim ;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String categorie;
    private Color color;
    private boolean unscheduled;
    private Etat etat;
    private boolean bloque;
    private boolean decomposable;



    //plannifier manuelle ou auto

    //re-planifier (string dureeSupp, String nvDeadline)          les deux


    public Tache(String nom, Duration dure, Priorite priorite, LocalDate dateLim, LocalDateTime dateDebut, LocalDateTime dateFin, String categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable) {
        this.nom = nom;
        this.dure = dure;
        this.priorite = priorite;
        this.dateLim = dateLim;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.categorie = categorie;
        this.color = color;
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

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
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
