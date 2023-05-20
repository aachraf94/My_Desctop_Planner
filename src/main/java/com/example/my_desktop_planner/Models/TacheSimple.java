package com.example.my_desktop_planner.Models;

import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TacheSimple extends Tache {

    private int periodicite;

    //    planifiées tous les n jours
    //void sePlanifier(String dateDebut , String dateFin)


    /*********************** constructor **************************/
    public TacheSimple(String nom, Duration dure, Priorite priorite, LocalDate dateLim, LocalDateTime dateDebut, LocalDateTime dateFin, String categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable, int periodicite, Etat etat1) {
        super(nom, dure, priorite, dateLim, dateDebut, dateFin, categorie, color, unscheduled, etat1, bloque, decomposable);
        this.periodicite = periodicite;

    }
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

}
