package com.example.my_desktop_planner.Models;

import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class TacheSimple extends Tache {

    private int periodicite;

    //    planifiées tous les n jours
    //void sePlanifier(String dateDebut , String dateFin)


    /*********************** constructor **************************/
    public TacheSimple(String nom, Duration dure, Priorite priorite, LocalDate dateLim, LocalDateTime dateDebut, LocalDateTime dateFin, Categorie categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable, int periodicite, Etat etat1) {
        super(nom, dure, priorite, dateLim, dateDebut, dateFin, categorie, color, unscheduled, etat, bloque, decomposable);
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

    public static TacheSimple generateRandomTask() {
        Random random = new Random();

        String nom = "Task " + random.nextInt(100); // Random task name
        Duration duree = Duration.ofHours(random.nextInt(10)); // Random duration up to 10 hours
        Priorite priorite = Priorite.values()[random.nextInt(Priorite.values().length)]; // Random priority
        LocalDate dateLimite = LocalDate.now().plusDays(random.nextInt(30)); // Random date within the next 30 days
        LocalDateTime dateDebut = LocalDateTime.now().plusHours(random.nextInt(24)); // Random start date within the next 24 hours
        LocalDateTime dateFin = dateDebut.plusHours(random.nextInt(24)); // Random end date after the start date
        Categorie categorie = Categorie.WORK; // Random category
        Color color = generateRandomColor(); // Random color
        boolean unscheduled = random.nextBoolean(); // Random unscheduled flag
        Etat etat = Etat.values()[random.nextInt(Etat.values().length)]; // Random state
        boolean bloque = random.nextBoolean(); // Random blocked flag
        boolean decomposable = random.nextBoolean(); // Random decomposable flag
        int periodicite = random.nextInt(10); // Random periodicity up to 10
        Etat etat1 = Etat.values()[random.nextInt(Etat.values().length)]; // Random state

        return new TacheSimple(nom, duree, priorite, dateLimite, dateDebut, dateFin, categorie, color, unscheduled, etat, bloque, decomposable, periodicite, etat1);
    }

    public static Color generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256); // Random value for red component (0-255)
        int green = random.nextInt(256); // Random value for green component (0-255)
        int blue = random.nextInt(256); // Random value for blue component (0-255)

        return Color.rgb(red, green, blue);
    }


    /************* Getters & Setters ****************/
    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }

}
