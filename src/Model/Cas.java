package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalTime;

public abstract class Cas {
    private Predmet predmet;
    private Profesor profesor;
    private Prostorija prostorija;
    private GrupaStudenata grupaStudenata;

    private StringProperty danOdrzavanja;
    private LocalTime pocetak;
    private LocalTime kraj;

    public Cas(Predmet predmet, Profesor profesor, Prostorija prostorija, GrupaStudenata grupaStudenata, String danOdrzavanja, LocalTime pocetak, LocalTime kraj) {
        this.predmet=predmet;
        this.profesor=profesor;
        this.prostorija=prostorija;
        this.grupaStudenata=grupaStudenata;
        this.danOdrzavanja=new SimpleStringProperty(danOdrzavanja);
        this.pocetak=pocetak;
        this.kraj=kraj;
    }

    public abstract boolean daLiJeValidnaProstorija(Prostorija p);
    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Prostorija getProstorija() {
        return prostorija;
    }

    public void setProstorija(Prostorija prostorija) {
        this.prostorija = prostorija;
    }

    public GrupaStudenata getGrupaStudenata() {
        return grupaStudenata;
    }

    public void setGrupaStudenata(GrupaStudenata grupaStudenata) {
        this.grupaStudenata = grupaStudenata;
    }

    public String getDanOdrzavanja() {
        return danOdrzavanja.get();
    }

    public StringProperty danOdrzavanjaProperty() {
        return danOdrzavanja;
    }

    public void setDanOdrzavanja(String danOdrzavanja) {
        this.danOdrzavanja.set(danOdrzavanja);
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalTime kraj) {
        this.kraj = kraj;
    }
}
