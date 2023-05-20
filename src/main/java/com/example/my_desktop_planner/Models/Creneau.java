package com.example.my_desktop_planner.Models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Creneau implements Decomposable<Creneau>{

    //a revoir
    private LocalDateTime hDebut;
    private LocalDateTime hFin;
    public Duration duree;
    public static Duration durationMin ;

   // private Duration dureeMin = Duration.ofMinutes(30);
    //    la durée maximale d’un créneau


    public Creneau(LocalDateTime hDebut, LocalDateTime hFin) {
        this.hDebut = hDebut;
        this.hFin = hFin;
        this.duree = Duration.between(hDebut,hFin);
    }


    public static Duration getDurationMin() {
        return durationMin;
    }

    public static void setDurationMin(int durationMin) {
        Creneau.durationMin = Duration.ofMinutes((int)durationMin);
    }

    @Override
    public Creneau decompose(Creneau obj) {
        return null;// a revoir
    }
}
