package com.example.my_desktop_planner.Models;

import java.io.*;
import java.util.*;

public class MyDesktopPlanner {
    private static MyDesktopPlanner instance;
    public  HashMap<Utilisateur, String> utilisateurs;

    private MyDesktopPlanner() {
        //on doit charger le fichier li fih les pseudo et les mdp et creer le hashMap
        this.utilisateurs = new HashMap<Utilisateur,String>();
        //parcourir le fichier
            //entregistrer les info de utilisateur + mdp
    }
    public static MyDesktopPlanner getInstance() { // pour assurer qu'il ya une seul instance de cette class
        if (instance == null) {
            // If the instance is null, create a new instance
            instance = new MyDesktopPlanner();
        }
        return instance;}


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
        } catch (IOException e) {
            throw new RuntimeException("Error saving users to file.", e);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadUsersFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Users.dat"))) {//users.dat c'est le fichier qui contient les utilisateurs , il sera ecrasé tout fois qu'on ajoute ou on supprime un utilisateur
            utilisateurs = (HashMap<Utilisateur, String>) in.readObject();//lire le fichier et le mettre dans le hashMap puisaue la class, est singleton , on peut le faire directement
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or there's an error reading it, ignore and start with an empty HashMap
        }
    }

}


//not completed*******************************************************************************************
//    public Utilisateur seConnecter(String pseudo,String mdp)
//    {
//        if( this.utilisateurs.containsKey(new Utilisateur(pseudo)))
//        {
//            Utilisateur user = this.utilisateurs.get(new Utilisateur(pseudo)).get
//            //on 2 choix
//            //1er voix
//
//        }
//        else {
//
//        }
//    }


