package com.example.my_desktop_planner.Models;

import java.time.LocalDateTime;

public class CreneauOccupe extends Creneau {

    private TacheSimple tacheSimple;

    public CreneauOccupe(LocalDateTime hDebut, LocalDateTime hFin, TacheSimple tacheSimple) {
        super(hDebut, hFin);
        this.tacheSimple = new TacheSimple(tacheSimple);
    }


}
