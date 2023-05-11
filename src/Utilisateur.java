import java.io.*;
import java.util.*;
public class Utilisateur implements Serializable {
    private String pseudo;
    private String mdp;
    private Planning planning;
    public void sauvgarder(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            objOut.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Utilisateur loadFromFile(String filePath) {
        Utilisateur utilisateur = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            utilisateur = (Utilisateur) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
    void seConnecter(String pseudo, String mdp){

    }
    void seDeconnecter(){

    }
    Void AjouterCreneauLibre(Creneau c){
        c= new Creneau();
    }
}
