package Model;

public class Profesor {
    private static int count = 0;
    private Integer ID;
    private String ime;

    public Profesor(String ime) {
        ID = count++;
        this.ime = new String(ime);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() { return ime; }
}
