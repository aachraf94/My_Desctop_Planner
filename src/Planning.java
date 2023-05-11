import java.util.Calendar;
import java.util.List;

public class Planning {
    Calendar calendirer;
    Creneau creneau;// c mieux de faire un crenau simple instead of creneau libre et creneau occupe , et on verifie si un creneau est libre dans le planing , si ya pas d'intersection
    List<Tache> taches;
    List<Creneau> creneauxLibres;// creneau libre = creneau - creneau occupe

void plannier(Tache t, Creneau c){
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


}
