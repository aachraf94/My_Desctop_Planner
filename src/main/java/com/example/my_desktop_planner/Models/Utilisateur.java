package com.example.my_desktop_planner.Models;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
public class Utilisateur implements Serializable {
    private String pseudo;
    private String mdp;
    private Planning planning;
    private static final long serialVersionUID = 1L;

    public Utilisateur(String pseudo,String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    

    public boolean seConnecter(String pseudo, String mdp){

        if (this.pseudo.equals(pseudo) && this.mdp.equals(mdp)) {
            System.out.println("Connexion réussie.");
            return true;
        } else {
            System.out.println("Pseudo ou mot de passe incorrect.");
            return false;
        }
    }

    public Boolean seDeconnecter(){
        return true;
    }
//    public void ajouterCreneauLibre(LocalDateTime heureDebut, LocalDateTime heureFin , Duration duree) {
//        Creneau c = new Creneau(heureDebut, heureFin);
////        planning.ajouterCreneauLibre(c);
//    }

//    public void supprimerCreneauLibre(Creneau creneau){
//
//        this.planning.supprimerCreneauLibre(creneau);
//
//    }

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
}
