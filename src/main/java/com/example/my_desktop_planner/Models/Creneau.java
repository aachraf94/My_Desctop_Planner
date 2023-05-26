package com.example.my_desktop_planner.Models;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Creneau implements Decomposable<Creneau>, Serializable {

    protected LocalDateTime hDebut;
    protected LocalDateTime hFin;
    protected Duration duree;

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
