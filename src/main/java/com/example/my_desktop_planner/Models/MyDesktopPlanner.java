package com.example.my_desktop_planner.Models;

import java.io.*;
import java.util.HashMap;

public class MyDesktopPlanner {
    private static MyDesktopPlanner instance;
    public static HashMap<Utilisateur, String> utilisateurs;

    public MyDesktopPlanner() {
        String filename = "Users.dat"; // Replace with your file name
        HashMap<Utilisateur, String> hashMap = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Read the object from the file and cast it to a HashMap
            hashMap = (HashMap<Utilisateur, String>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading data from file.");
        }
        // Use the loaded HashMap
        if (hashMap != null) {
            utilisateurs = new HashMap<Utilisateur, String>(hashMap);
            System.out.println("Loaded data from file:");
        }
    }

    public static MyDesktopPlanner getInstance() { // pour assurer qu'il ya une seul instance de cette class
        if (instance == null) {
            // If the instance is null, create a new instance
            instance = new MyDesktopPlanner();
        }
        return instance;
    }

    public HashMap<Utilisateur, String> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(HashMap<Utilisateur, String> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }


    //on va faire Method registre

    public boolean addUser(Utilisateur user, String mdp) {// verifié si l'utilisateur existe ou non
        if (!utilisateurs.containsKey(user)) {
            utilisateurs.put(user, mdp);// ajouter le couple utilisateur et mdp
            System.out.println("User '" + user.getPseudo() + "' added successfully.");
            saveUsersToFile();
        } else {
            System.out.println("User '" + user.getPseudo() + "' already exists.");
        }
        return !utilisateurs.containsKey(user);
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
    public HashMap<Utilisateur, String> loadUsersFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Users.dat"))) {//users.dat c'est le fichier qui contient les utilisateurs , il sera ecrasé tout fois qu'on ajoute ou on supprime un utilisateur
            utilisateurs = (HashMap<Utilisateur, String>) in.readObject();
            System.out.println("Loaded successfully");//lire le fichier et le mettre dans le hashMap puisaue la class, est singleton , on peut le faire directement
            return utilisateurs;
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or there's an error reading it, ignore and start with an empty HashMap
        }
        return null;
    }

    public Utilisateur findUser(String pseudo, String mdp) {
        this.loadUsersFromFile();
        MyDesktopPlanner planner = MyDesktopPlanner.getInstance();
        HashMap<Utilisateur, String> utilisateurs = planner.getUtilisateurs();

        for (Utilisateur user : utilisateurs.keySet()) {
            if (user.getPseudo().equals(pseudo) && user.getMdp().equals(mdp)) {
                return user;
            }
        }

        return null; // User not found
    }

}