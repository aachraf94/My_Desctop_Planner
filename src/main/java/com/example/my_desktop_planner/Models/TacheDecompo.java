package com.example.my_desktop_planner.Models;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class TacheDecompo extends Tache implements Decomposable , Serializable {
    /******* attribut ***********/
    private int nbSousTache;
    private ArrayList<TacheSimple> sousTache;
    /********** Constructeur ********************/
    public TacheDecompo(String nom, Duration dure, Priorite priorite, LocalDate dateLim, LocalDate dateDebut, LocalDate dateFin, Categorie categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable, int nbSousTache, ArrayList<TacheSimple> sousTache) {
        super(nom, dure, priorite, dateLim, dateDebut, dateFin, categorie, color, unscheduled, etat, bloque, decomposable);
        this.nbSousTache = nbSousTache;
        this.sousTache = new ArrayList<>(sousTache);
    }
    public TacheDecompo(String nom, Duration dure, Priorite priorite, LocalDate dateLim, LocalDate dateDebut, LocalDate dateFin, Categorie categorie, Color color, boolean unscheduled, Etat etat, boolean bloque, boolean decomposable, int nbSousTache) {
        super(nom, dure, priorite, dateLim, dateDebut, dateFin, categorie, color, unscheduled, etat, bloque, decomposable);
        this.nbSousTache = nbSousTache;
    }

    /*********************************************/

    @Override
    public Object decompose(Object obj) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    /************** Getters and Setters ********************/
    public int getNbSousTache() {
        return nbSousTache;
    }

    public void setNbSousTache(int nbSousTache) {
        this.nbSousTache = nbSousTache;
    }

    public ArrayList<TacheSimple> getSousTache() {
        return sousTache;
    }

    public void setSousTache(ArrayList<TacheSimple> sousTache) {
        this.sousTache = new ArrayList<TacheSimple>(sousTache);
    }


}
