package Model;

import java.time.LocalTime;

public class LaboratorijskaVjezba extends Cas {
    private boolean daLiJeElektronicka;
    private boolean daLiJeRacunarska;
    public LaboratorijskaVjezba(Predmet predmet, Profesor profesor, Prostorija prostorija, GrupaStudenata grupaStudenata, String danOdrzavanja, LocalTime pocetak, LocalTime kraj,boolean daLiJeElektronicka, boolean daLiJeRacunarska) {
        super(predmet,profesor,prostorija,grupaStudenata,danOdrzavanja,pocetak,kraj);
        this.daLiJeElektronicka=daLiJeElektronicka;
        this.daLiJeRacunarska=daLiJeRacunarska;
    }
    @Override
    public boolean daLiJeValidnaProstorija(Prostorija prostorija) {
        if (prostorija.getKapacitet()>=super.getGrupaStudenata().getBrojStudenata()) {
            if (daLiJeRacunarska && prostorija.isDaLiJeRacunarskiLab())
                return true;
            if (daLiJeElektronicka && prostorija.isDaLiJeElektronickiLab())
                return true;
        }
        return false;
    }
    public LaboratorijskaVjezba(int id) {
        super(id);
    }
}
