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
