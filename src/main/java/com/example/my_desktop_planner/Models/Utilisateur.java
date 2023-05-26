package com.example.my_desktop_planner.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Utilisateur implements Serializable {

    public Planning planning;
    private String pseudo;
    private String mdp;

    /*********************** Constrictors ******************************/

    public Utilisateur(String pseudo, String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.planning = new Planning();
    }
    public Utilisateur(String pseudo)
    {
        this.pseudo=pseudo;
    }
    /*********************** Fin Constrictors ******************************/

    @Override
    public int hashCode() {
        return getPseudo().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.pseudo.equals(((Utilisateur) obj).getPseudo());
    }

    public void afficher() {
        System.out.println("pseudo : " + pseudo);
        System.out.println("mdp : " + mdp);
        //System.out.println("planning : "+planning);
        //System.out.println("taches : "+taches);
    }


    public void planifierAuto(ArrayList<Tache> tachesUnschedueled) {
        ArrayList<Tache> tachesHigh = new ArrayList<Tache>();
        ArrayList<Tache> tachesMedium = new ArrayList<Tache>();
        ArrayList<Tache> tachesLow = new ArrayList<Tache>();

        for (Tache tache : tachesUnschedueled) {
            if (tache.getPriorite() == Priorite.HIGHT) {
                tachesHigh.add(tache);
            } else if (tache.getPriorite() == Priorite.MEDIUM) {
                tachesMedium.add(tache);
            } else if (tache.getPriorite() == Priorite.LOW) {
                tachesLow.add(tache);
            }
        }
//        tachesHigh.sort();
    }


    /********* Setters et Getters ********************/

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Planning getPlanning() {
        return planning;
    }
    /************ Fin getters and setters ********************/

}
