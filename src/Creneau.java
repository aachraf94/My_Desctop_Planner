import java.time.Duration;

public class Creneau implements Decomposable<Creneau>{

    //a revoir
    private String hDebut;
    private String hFin;
    private int duree;

    private Duration dureeMin = Duration.ofMinutes(30);
    //    la durée maximale d’un créneau

    public Creneau(String hDebut, String hFin, int duree, Duration dureeMin) {
        this.hDebut = hDebut;
        this.hFin = hFin;
        this.duree = duree;
        this.dureeMin = dureeMin;// c bien comme

    }

    @Override
    public Creneau decompose(Creneau obj) {
        return null;// a revoir
    }
}
