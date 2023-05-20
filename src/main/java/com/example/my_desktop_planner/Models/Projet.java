package com.example.my_desktop_planner.Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Delayed;

public class Projet {
    private String nom;
    private String description;
    private LinkedList<Tache> taches ;//ou linkedlist

    private Etat etat;

    public Projet(String nom, String description, List<Tache> taches) {
        this.nom = nom;
        this.description = description;
        this.taches = new LinkedList<Tache>(taches);
    }

    public Etat updateEtat() {

        Iterator<Tache> iterator = this.taches.iterator();
        Boolean inProgress = false;
        Boolean complet = true;
        Boolean delayed = false;
        Boolean cancelled = true;

        while (iterator.hasNext())
        {
            Tache tache= iterator.next();
            if (tache.getEtat() == Etat.IN_PROGRESS)
            {
                inProgress = true;
            }
            if (tache.getEtat() == Etat.DELAYED)
            {
                delayed = true;
            }
            complet = complet && (tache.getEtat() == Etat.COMPLETED);
            inProgress = inProgress && (tache.getEtat() != Etat.CANCELLED) && (tache.getEtat() != Etat.DELAYED) ;
            cancelled = cancelled && (tache.getEtat() == Etat.CANCELLED);
        }
        //return the value
        if (complet)
        {
            return Etat.COMPLETED;
        } else if (inProgress) {
            return Etat.IN_PROGRESS;
        } else if (delayed) {
            return Etat.DELAYED;
        } else if (cancelled) {
            return Etat.CANCELLED;
        }
        else {
            return Etat.NOT_REALIZED;
        }
    }
}
