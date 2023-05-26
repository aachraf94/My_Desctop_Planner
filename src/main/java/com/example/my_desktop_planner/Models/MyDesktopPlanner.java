package com.example.my_desktop_planner.Models;

import com.example.my_desktop_planner.HelloApplication;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

import static com.example.my_desktop_planner.HelloApplication.*;

public class MyDesktopPlanner implements Serializable {
    private static MyDesktopPlanner instance;
    private HashMap<Utilisateur, String> utilisateurs;

    public MyDesktopPlanner() {
        this.utilisateurs = new HashMap<Utilisateur, String>();
        //add users without creneaux libres
        this.utilisateurs.put(new Utilisateur("admin","admin"),"admin");
        this.utilisateurs.put(new Utilisateur("achraf","achraf"),"achraf");
        this.utilisateurs.put(new Utilisateur("chamel","chamel"),"chamel");
    }


    public HashMap<Utilisateur, String> getUtilisateurs() {
        return utilisateurs;
    }






    //on va faire Method registre

    public void addUser(Utilisateur user, String mdp) {// verifié si l'utilisateur existe ou non
        if (!utilisateurs.containsKey(user)) {
            utilisateurs.put(user, mdp);// ajouter le couple utilisateur et mdp
            System.out.println("User '" + user.getPseudo() + "' added successfully.");
            saveUsersToFile();
        } else {
            System.out.println("User '" + user.getPseudo() + "' already exists.");
        }
    }

    public void removeUser(Utilisateur user) {// verifié si l'utilisateur existe ou non
        if (utilisateurs.containsKey(user)) {
            utilisateurs.remove(user);// supprimer le couple utilisateur et mdp
            System.out.println("User '" + user.getPseudo() + "' removed successfully.");
            saveUsersToFile();
        } else {
            System.out.println("User '" + user.getPseudo() + "' does not exist.");
        }
    }

    public void saveUsersToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Users.dat"))) {// on cree un nouveu flux de sortie pour le fichier Users.dat pour le modifier
            out.writeObject(utilisateurs);// enregistrer le hashMap dans le fichier
            System.out.println("Saved successfully");
        } catch (IOException e) {
            throw new RuntimeException("Error saving users to file.", e);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadUsersFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Users.dat"))) {//users.dat c'est le fichier qui contient les utilisateurs , il sera ecrasé tout fois qu'on ajoute ou on supprime un utilisateur
            utilisateurs = (HashMap<Utilisateur, String>) in.readObject();
            System.out.println("Loaded successfully");//lire le fichier et le mettre dans le hashMap puisaue la class, est singleton , on peut le faire directement
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or there's an error reading it, ignore and start with an empty HashMap
        }
    }


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