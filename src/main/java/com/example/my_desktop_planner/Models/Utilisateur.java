package com.example.my_desktop_planner.Models;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
public class Utilisateur implements Serializable {
    private String pseudo;
    private String mdp;
    public Planning planning;
    private static final long serialVersionUID = 1L;

    public Utilisateur(String pseudo,String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
    }
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

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }




    @Override
    public int hashCode() {
        return getPseudo().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.pseudo.equals(((Utilisateur)obj).getPseudo());
    }
    public void afficher ()
{
        System.out.println("pseudo : "+pseudo);
        System.out.println("mdp : "+mdp);
        //System.out.println("planning : "+planning);
        //System.out.println("taches : "+taches);
    }
    public ArrayList<Tache> getTasks(LocalDate date)
    {   ArrayList<Tache> tasks = new ArrayList<Tache>();
        for (Tache tache : planning.getTachePlannifies()) {
            if (date.getDayOfYear() == tache.getDateDebut().getDayOfYear() && date.getYear() == tache.getDateDebut().getYear() && date.getMonth() ==tache.getDateDebut().getMonth())
            {
               tasks.add(tache);
            }

        }
        return tasks ;
    }

}
