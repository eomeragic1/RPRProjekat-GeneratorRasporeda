package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Podaci {
    public static GrupaStudenata[] grupaStudenata;
    public static Profesor[] profesori;
    public static Prostorija[] prostorije;
    public static Cas[] casovi;
    public static int casovaPoDanu;
    public static int brojRadnihDanaUSedmici;
    public static ArrayList<String> radniDani = new ArrayList<>();
    public static Predmet[] predmeti;

    public static void setRadniDani(HashMap<String, Boolean> mapaDana) {
        radniDani.clear();
        if(mapaDana.get("ponedjeljak")){
            radniDani.add("Ponedjeljak");
        }
        if(mapaDana.get("utorak")){
            radniDani.add("Utorak");
        }
        if(mapaDana.get("srijeda")){
            radniDani.add("Srijeda");
        }
        if(mapaDana.get("četvrtak")){
            radniDani.add("Četvrtak");
        }
        if(mapaDana.get("petak")){
            radniDani.add("Petak");
        }
        if(mapaDana.get("subota")){
            radniDani.add("Subota");
        }
    }


    public static void setGrupaStudenata(GrupaStudenata[] grupaStudenata) {
        Podaci.grupaStudenata = grupaStudenata;
    }

    public static void setProfesori(Profesor[] profesori) {
        Podaci.profesori = profesori;
    }

    public static void setProstorije(Prostorija[] prostorije) {
        Podaci.prostorije = prostorije;
    }

    public static void setCasovi(Cas[] casovi) {
        Podaci.casovi = casovi;
    }

    public static void setCasovaPoDanu(int casovaPoDanu) {
        Podaci.casovaPoDanu = casovaPoDanu;
    }

    public static void setBrojRadnihDanaUSedmici(int brojRadnihDanaUSedmici) {
        Podaci.brojRadnihDanaUSedmici = brojRadnihDanaUSedmici;
    }

    public static void setPredmeti(Predmet[] predmeti) {
        Podaci.predmeti = predmeti;
    }

    public static Cas dajCasPremaID(int id) {
        for (Cas cas : casovi) {
            if (cas.getID() == id)
                return cas;
        }
        return new Predavanje(-1);
    }

    public static Prostorija dajProstorijuPremaID(int id) {
        for (Prostorija prostorija : prostorije) {
            if (prostorija.getID() == id)
                return prostorija;
        }
        return null;
    }
}
