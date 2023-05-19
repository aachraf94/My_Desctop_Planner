package com.example.my_desktop_planner.Models;

import java.util.List;

public class Projet {
    private String nom;
    private String description;
    private List<Tache> taches ;//ou linkedlist
    public Projet(String nom, String description, List<Tache> taches) {
        this.nom = nom;
        this.description = description;
        this.taches = taches;
    }

}
