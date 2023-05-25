package com.example.my_desktop_planner.Models;

import java.time.Duration;
import java.time.LocalDateTime;

public class Creneau implements Decomposable<Creneau> {

    protected LocalDateTime hDebut;
    protected LocalDateTime hFin;
    protected Duration duree;

    // private Duration dureeMin = Duration.ofMinutes(30);
    //    la durée maximale d’un créneau


    public Creneau(LocalDateTime hDebut, LocalDateTime hFin) {
        this.hDebut = hDebut;
        this.hFin = hFin;
        this.duree = Duration.between(hDebut, hFin);
    }


    public Duration getDurationMin() {
        return duree;
    }


    @Override
    public Creneau decompose(Creneau obj) {
        return null;// a revoir
    }

    @Override
    public String toString() {
        return "\nCreneau{" +
                "\nhDebut=" + hDebut +
                "\n, hFin=" + hFin +
                "\n, duree=" + duree +
                '}';
    }
}
