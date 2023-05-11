import java.util.List;

public class Projet {
    private String nom;
    private String description;
    private List<Tache> taches ;//ou linkedlist
    public Projet(String name, String description, List<Tache> taches) {
        this.nom = name;
        this.description = description;
        this.taches = taches;
    }

}
