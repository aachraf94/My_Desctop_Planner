import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Planning {
    Calendar calendirer;
    // c mieux de faire un crenau simple instead of creneau libre et creneau occupe , et on verifie si un creneau est libre dans le planing , si ya pas d'intersection
    List<Tache> taches;
    Date dateDebut;
    Date dateFin;
    List<Creneau> creneaus;// creneau libre = creneau - creneau occupe

    void plannier(Tache t, Creneau c) {
        //if (c.estLibre()){
        //t.setCreneau(c);
        //taches.add(t);
        //}
    }
    //void replanifier (Planning p) {
    //   if (p.valideDecomp()){
    //    taches.add(p.taches);}


    //Pour que l’application puisse effectuer la
    //planification, l’utilisateur spécifie, en premier, ses créneaux libres durant lesquels les tâches peuvent
    //être programmées


    //plannifier      -->  décomposer le créneau libre selon la durée de la tache
    // replanifier  -->  décomposer le créneau libre selon la durée de la tache
    void ajouterCreneauLibre(Creneau c) {
        creneaus.add(c);
    }

    void supprimerCreneauLibre(Creneau c) {
        creneaus.remove(c);
    }
    //definir les crzeneau libre d'une facons globale
}
// date debut , date fin de planning