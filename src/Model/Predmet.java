package Model;

import javafx.beans.property.StringProperty;

public class Predmet {
    private int ID;
    private Profesor profesor;
    private String imePredmeta;

    public Predmet(int ID, Profesor profesor, String imePredmeta) {
        this.ID=ID;
        this.profesor=profesor;
        this.imePredmeta=imePredmeta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getImePredmeta() {
        return imePredmeta;
    }

    public void setImePredmeta(String imePredmeta) {
        this.imePredmeta = imePredmeta;
    }
}
