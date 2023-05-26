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

//    public boolean seConnecter(String pseudo, String mdp) {
//
//        if (this.pseudo.equals(pseudo) && this.mdp.equals(mdp)) {
//            System.out.println("Connexion réussie.");
//            return true;
//        } else {
//            System.out.println("Pseudo ou mot de passe incorrect.");
//            return false;
//        }
//    }

//    public Boolean seDeconnecter() {
//        return true;
//    }
//    public void ajouterCreneauLibre(LocalDateTime heureDebut, LocalDateTime heureFin , Duration duree) {
//        Creneau c = new Creneau(heureDebut, heureFin);
////        planning.ajouterCreneauLibre(c);
//    }

//    public void supprimerCreneauLibre(Creneau creneau){
//
//        this.planning.supprimerCreneauLibre(creneau);
//
//    }



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

    public ArrayList<Tache> getTasks(LocalDate date) {
        ArrayList<Tache> tasks = new ArrayList<Tache>();
        for (Tache tache : planning.getTachePlannifies()) {
            if (date.getDayOfYear() == tache.getDateDebut().getDayOfYear() && date.getYear() == tache.getDateDebut().getYear() && date.getMonth() == tache.getDateDebut().getMonth()) {
                tasks.add(tache);
            }

        }
        return tasks;
    }

    public void ajouterCreneauLibre(CreneauLibre creneauLibre) {
        if (this.planning == null) {
            this.planning = new Planning();
        } else {
            this.planning.ajouterCreneauLibre(creneauLibre);
        }

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
