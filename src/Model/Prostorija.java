package Model;

public class Prostorija {
    private static int count = 0;
    private int ID;
    private int kapacitet;
    private boolean daLiImaProjektor;
    private boolean daLiJeAmfiteatar;
    private boolean daLiJeRacunarskiLab;
    private String imeProstorije;
    private boolean daLiJeElektronickiLab;

    public Prostorija(String imeProstorije, int kapacitet, boolean daLiImaProjektor, boolean daLiJeAmfiteatar, boolean daLiJeRacunarskiLab, boolean daLiJeElektronickiLab) {
        this.imeProstorije=imeProstorije;
        this.kapacitet=kapacitet;
        this.daLiImaProjektor=daLiImaProjektor;
        this.daLiJeAmfiteatar=daLiJeAmfiteatar;
        this.daLiJeElektronickiLab=daLiJeElektronickiLab;
        this.daLiJeRacunarskiLab=daLiJeRacunarskiLab;
        ID = count++;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public boolean isDaLiImaProjektor() {
        return daLiImaProjektor;
    }

    public void setDaLiImaProjektor(boolean daLiImaProjektor) {
        this.daLiImaProjektor = daLiImaProjektor;
    }

    public boolean isDaLiJeAmfiteatar() {
        return daLiJeAmfiteatar;
    }

    public void setDaLiJeAmfiteatar(boolean daLiJeAmfiteatar) {
        this.daLiJeAmfiteatar = daLiJeAmfiteatar;
    }

    public boolean isDaLiJeRacunarskiLab() {
        return daLiJeRacunarskiLab;
    }

    public void setDaLiJeRacunarskiLab(boolean daLiJeRacunarskiLab) {
        this.daLiJeRacunarskiLab = daLiJeRacunarskiLab;
    }

    public String getImeProstorije() {
        return imeProstorije;
    }

    public void setImeProstorije(String imeProstorije) {
        this.imeProstorije = imeProstorije;
    }

    public boolean isDaLiJeElektronickiLab() {
        return daLiJeElektronickiLab;
    }

    public void setDaLiJeElektronickiLab(boolean daLiJeElektronickiLab) {
        this.daLiJeElektronickiLab = daLiJeElektronickiLab;
    }

    public int getID() {
        return ID;
    }
}
