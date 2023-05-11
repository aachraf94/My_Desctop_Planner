import java.util.Date;
import java.util.HashMap;
import java.util.List ;
import java.util.Map;
public class MyDesktopPlanner {
    private Map<String, Utilisateur> Utilisateurs;

    public MyDesktopPlanner() {
        this.Utilisateurs = new HashMap<>();
    }

    public void addUser(String username) {
        if (!Utilisateurs.containsKey(username)) {
            //Utilisateur user = new Utilisateur(username);
            //users.put(username, user);
            System.out.println("User '" + username + "' added successfully.");
        } else {
            System.out.println("User '" + username + "' already exists.");
        }
    }


}
