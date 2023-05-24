package com.example.my_desktop_planner.Models;

import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class TacheDecompo implements Decomposable<TacheDecompo>{


    private int nbSousTache ;
    private LinkedList<TacheSimple> sousTache;

    @Override
    public TacheDecompo decompose(TacheDecompo obj) {
        return null;// a revoir
    }

    //constructeur
    //add tache
    //
    public TacheDecompo (String nom, Duration dure, Priorite priorite, LocalDate dateLim, java.time.LocalDateTime dateDebut, LocalDateTime dateFin, String categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable) {
        //super(nom , dure , priorite , dateLim , dateDebut , dateFin , categorie , color , unscheduled , etat , bloque , decomposable);
        this.nbSousTache = nbSousTache;
        this.sousTache = sousTache;
    }
    public TacheDecompo(TacheDecompo tacheDecompo)
    {
        this.nbSousTache = tacheDecompo.getNbSousTache();
        this.sousTache.addAll(tacheDecompo.getSousTache());


//        destinationList.addAll(sourceList);
    }


    public int getNbSousTache() {
        return nbSousTache;
    }

    public void setNbSousTache(int nbSousTache) {
        this.nbSousTache = nbSousTache;
    }

    public LinkedList<TacheSimple> getSousTache() {
        return sousTache;
    }

    public void setSousTache(LinkedList<TacheSimple> sousTache) {
        this.sousTache = sousTache;
    }
}
