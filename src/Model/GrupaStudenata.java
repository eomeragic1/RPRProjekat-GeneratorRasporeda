package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GrupaStudenata {
    private static int count=0;

    public int getBrojStudenata() {
        return brojStudenata.get();
    }

    public SimpleIntegerProperty brojStudenataProperty() {
        return brojStudenata;
    }

    public void setBrojStudenata(int brojStudenata) {
        this.brojStudenata.set(brojStudenata);
    }

    private SimpleIntegerProperty brojStudenata;
    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    private SimpleStringProperty naziv;
    private SimpleIntegerProperty ID;

    public GrupaStudenata(String naziv, Integer ID,Integer brojStudenata) {
        this.naziv=new SimpleStringProperty(naziv);
        this.ID=new SimpleIntegerProperty(count++);
        this.brojStudenata=new SimpleIntegerProperty(brojStudenata);
    }
}
