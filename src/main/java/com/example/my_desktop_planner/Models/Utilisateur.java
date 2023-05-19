package com.example.my_desktop_planner.Models;

import java.io.*;
import java.util.*;
public class Utilisateur implements Serializable {
    private String pseudo;
//    Aspect sécurite on doit relver mdp from utilisateur claa
    private String mdp;
    private Planning planning;
    private List<Tache> taches;// non planifier
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
    public void ajouterCreneauLibre(String heureDebut, String heureFin , int duree) {
        Creneau c = new Creneau(heureDebut, heureFin, duree);
        planning.ajouterCreneauLibre(c);
    }

    public void supprimerCreneauLibre(String heureDebut, String heureFin , int duree){
        Creneau c = new Creneau(heureDebut, heureFin, duree);
        planning.supprimerCreneauLibre(c);
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

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
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
