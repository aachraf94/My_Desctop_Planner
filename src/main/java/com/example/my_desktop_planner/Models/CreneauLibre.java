package com.example.my_desktop_planner.Models;

import java.time.LocalDateTime;

public class CreneauLibre extends Creneau {

    public CreneauLibre(LocalDateTime hDebut, LocalDateTime hFin) {
        super(hDebut, hFin);

    }

    @Override
    public String toString() {
        return ("Creneau{" +
            "\nhDebut=" + hDebut +
                    "\n, hFin=" + hFin +
                    "\n, duree=" + duree +
                    '}');
    }
}
