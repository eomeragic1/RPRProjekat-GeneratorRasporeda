package Model;

public class Korisnik {
    private String ime;
    private String prezime;
    private String jmbg;

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    private String korisnickoIme;
    private String lozinka;
    private int tip;

    public Korisnik(String ime, String prezime, String jmbg, String korisnickoIme, String lozinka, int tip) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.korisnickoIme=korisnickoIme;
        this.lozinka = lozinka;
        this.tip = tip;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
