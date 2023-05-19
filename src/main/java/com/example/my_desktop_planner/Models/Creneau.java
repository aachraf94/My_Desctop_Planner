package com.example.my_desktop_planner.Models;

import java.time.Duration;

public class Creneau implements Decomposable<Creneau>{

    //a revoir
    private String hDebut;
    private String hFin;
    private int duree=30;

   // private Duration dureeMin = Duration.ofMinutes(30);
    //    la durée maximale d’un créneau

    public Creneau(String hDebut, String hFin, int duree/*, Duration dureeMin*/) {
        this.hDebut = hDebut;
        this.hFin = hFin;
        this.duree = duree;
        //this.dureeMin = dureeMin;// c bien comme pour defini la duree min des creneau
    }
    public Creneau() {}

    @Override
    public Creneau decompose(Creneau obj) {
        return null;// a revoir
    }
}
