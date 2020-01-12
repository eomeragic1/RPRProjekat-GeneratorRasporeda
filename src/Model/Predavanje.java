package Model;

import java.time.LocalTime;

public class Predavanje extends Cas {
    public Predavanje(Predmet predmet, Profesor profesor, Prostorija prostorija, GrupaStudenata grupaStudenata, String danOdrzavanja, LocalTime pocetak, LocalTime kraj) {
        super(predmet,profesor,prostorija,grupaStudenata,danOdrzavanja,pocetak,kraj);
    }
    @Override
    public boolean daLiJeValidnaProstorija(Prostorija prostorija) {
        if ((prostorija.isDaLiJeAmfiteatar() || prostorija.isDaLiImaProjektor()) && prostorija.getKapacitet()>=super.getGrupaStudenata().getBrojStudenata())
            return true;
        return false;
    }

}
