package com.example.my_desktop_planner.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CreneauLibre extends Creneau implements Serializable {

    public CreneauLibre(LocalDateTime hDebut, LocalDateTime hFin) {
        super(hDebut, hFin);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
