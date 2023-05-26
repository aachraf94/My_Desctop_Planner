package com.example.my_desktop_planner.Models;

import com.example.my_desktop_planner.HelloApplication;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

import static com.example.my_desktop_planner.HelloApplication.*;

public class MyDesktopPlanner implements Serializable {
    private HashMap<Utilisateur, String> utilisateurs;

    /**************  constructor  ****************************************************/

    public MyDesktopPlanner() {
        this.utilisateurs = new HashMap<Utilisateur, String>();
        //add users without creneaux libres
        this.utilisateurs.put(new Utilisateur("admin","admin"),"admin");
        this.utilisateurs.put(new Utilisateur("achraf","achraf"),"achraf");
        this.utilisateurs.put(new Utilisateur("chamel","chamel"),"chamel");
    }
    /*********************************************************************************/

    public HashMap<Utilisateur, String> getUtilisateurs() {
        return utilisateurs;
    }

    /*********************************************************************************/



//    cheked and updated
    public boolean isExist (String pseudo)
    {
        Utilisateur utilisateurRecherche = new Utilisateur(pseudo);
        if (utilisateurs.containsKey(utilisateurRecherche)) {
            System.out.println("isExist found user");
            return true;
        } else {
            System.out.println("Function findUser can't find the user!");
            return false;
        }
    }


//    checked and updated
    public Utilisateur findUser(String pseudo, String mdp) {
        Utilisateur utilisateurRecherche = new Utilisateur(pseudo);

        if (utilisateurs.containsKey(utilisateurRecherche)) {
            for (Utilisateur user : utilisateurs.keySet()) {
                if (user.getPseudo().equals(pseudo) && user.getMdp().equals(mdp)) {
                    return user;
                }
            }
        } else {
            System.out.println("Function findUser can't find the user!");
        }
        return null;
    }


//    checked and updated
    public void sauvegarder() {
        // Check if user is logged in
        if (utilisateurCourant != null) {
            utilisateurs.remove(utilisateurCourant);
            utilisateurs.put(utilisateurCourant, utilisateurCourant.getMdp());
            System.out.println("utilisateurCourant est bien sauvegarder");
        }

        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(FILE_PATH))) {
            out.writeObject(myDesktopPlanner);
            System.out.println("myDesktopPlanner est bien sauvegarder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}