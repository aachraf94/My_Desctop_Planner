import java.util.*;

public class MyDesktopPlanner {
    private Map<Utilisateur, String> utilisateurs;

    public MyDesktopPlanner() {
        //on doit charger le fichier li fih les pseudo et les mdp et creer le hashMap
        this.utilisateurs = new HashMap<Utilisateur,String>();
        //parcourir le fichier
            //entregistrer les info de utilisateur + mdp
    }

    //on va faire Method registre
    public void addUser(String username) {
        if (!utilisateurs.containsKey(username)) {
            //Utilisateur user = new Utilisateur(username);
            //users.put(username, user);
            System.out.println("User '" + username + "' added successfully.");
        } else {
            System.out.println("User '" + username + "' already exists.");
        }
    }
    public void register(Utilisateur user, String mpd) {

        if (!utilisateurs.containsKey(user.getPseudo())) {
            utilisateurs.put(user,mpd);
            System.out.println("User '" + user.getPseudo() + "' registered successfully.");
        } else {
            System.out.println("User '" + user.getPseudo() + "' already exists.");
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


}
