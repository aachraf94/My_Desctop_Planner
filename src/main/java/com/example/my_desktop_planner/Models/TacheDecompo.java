package com.example.my_desktop_planner.Models;

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

}
