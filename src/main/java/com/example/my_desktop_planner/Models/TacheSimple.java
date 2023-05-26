package com.example.my_desktop_planner.Models;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class TacheSimple extends Tache implements Serializable {
    private int periodicite;

    public TacheSimple(String nom, Duration dure, Priorite priorite, LocalDate dateLim, LocalDate dateDebut, LocalDate dateFin, Categorie categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable, int periodicite) {
        super(nom, dure, priorite, dateLim, dateDebut, dateFin, categorie, color, unscheduled, etat, bloque, decomposable);
        this.periodicite = periodicite;
    }
    /*********************** constructor **************************/
    public TacheSimple(TacheSimple t) {
        super(
                t.getNom(),
                t.getDure(),
                t.getPriorite(),
                t.getDateLim(),
                t.getDateDebut(),
                t.getDateFin(),
                t.getCategorie(),
                t.getColor(),
                t.isUnscheduled(),
                t.getEtat(),
                t.isBloque(),
                t.isDecomposable()
        );
        this.periodicite = t.getPeriodicite();

    }



    /************* Getters & Setters ****************/
    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
