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

    public void addTask(String username, String taskName, int duration, Priorite priority, Date deadline, Cater category, Color color) {
        Utilisateur user = Utilisateurs.get(username);
        if (user != null) {
            Tache task = new Tache(taskName, duration, priority, deadline, category, color) {
            };
            user.getPersonalTasks().add(task);
            System.out.println("Task '" + taskName + "' added to user '" + username + "' successfully.");
        } else {
            System.out.println("User '" + username + "' does not exist.");
        }
    }

    public void createProject(String username, String projectName, String projectDescription, List<Task> tasks) {
        User user = users.get(username);
        if (user != null) {
            Project project = new Project(projectName, projectDescription, tasks);
            // Add project to user's projects list or perform desired operations
            System.out.println("Project '" + projectName + "' created for user '" + username + "' successfully.");
        } else {
            System.out.println("User '" + username + "' does not exist.");
        }
    }
}
