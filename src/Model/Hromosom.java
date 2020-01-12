package Model;

import java.util.*;

public class Hromosom {
    public int duzinaHromosoma = Podaci.brojRadnihDanaUSedmici*Podaci.casovaPoDanu*Podaci.prostorije.length;
    private int[] termini = new int[duzinaHromosoma];
    private HashMap<Integer, Integer> mapaCasova = new HashMap<>(); // casID - terminID
    public double fitSkor = 0;

    Hromosom(boolean daLiJeSlucajan) {
        Arrays.fill(termini,-1);
        mapaCasova.clear();
        fitSkor=0;
        if (daLiJeSlucajan) {
            Random rand = new Random();
            for (int i=0; i<Podaci.casovi.length; i++) {
                int terminID = rand.nextInt(duzinaHromosoma);
                while (termini[terminID]!=-1) {
                    terminID = rand.nextInt(duzinaHromosoma);
                }
                int casID = Podaci.casovi[i].getID();
                termini[terminID]=casID;
                mapaCasova.put(casID,terminID);
            }
        }
    }

    public void izracunajFit() {
        int skorKvaliteta = 0;
        Iterator<?> it = mapaCasova.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry par = (Map.Entry) it.next();
            int casID = (int) par.getKey();
            int terminID = (int) par.getValue();
            skorKvaliteta += izracunajSkorGena(casID,terminID);
        }
        fitSkor = skorKvaliteta/(3.0 *mapaCasova.size());
    }

    private int izracunajSkorGena(int casID, int terminID) {
        int skorGena = 0;
        int prostorijaID = Podaci.prostorije[(terminID*Podaci.prostorije.length*Podaci.brojRadnihDanaUSedmici/termini.length)%Podaci.prostorije.length].getID();
        if (Podaci.dajCasPremaID(casID).daLiJeValidnaProstorija(Podaci.dajProstorijuPremaID(prostorijaID)))
            skorGena++;
        if (daLiJeSlobodanProfesor(terminID))
            skorGena++;
        if (daLiJeSlobodnaGrupaStudenata(terminID))
            skorGena++;
        return skorGena;
    }

    private boolean daLiJeSlobodnaGrupaStudenata(int terminID) {

    }

    private boolean daLiJeSlobodanProfesor(int terminID) {
        Profesor p = Podaci.dajCasPremaID(termini[terminID]).getProfesor();
        int brojCasovaProfesora = 0;
        int k = terminID%Podaci.casovaPoDanu;
        int danID = (terminID*Podaci.brojRadnihDanaUSedmici/duzinaHromosoma)%Podaci.brojRadnihDanaUSedmici;
        k+= danID * (duzinaHromosoma/Podaci.brojRadnihDanaUSedmici);
        int brojProstorija = Podaci.prostorije.length;
        for (int i=0; i<brojProstorija;i++,k+=Podaci.casovaPoDanu) {
            if (termini[k]==-1)
                continue;
            else if (Podaci.dajCasPremaID(termini[k]).getProfesor().equals(p)){
                brojCasovaProfesora++;
            }
        }
        if (brojCasovaProfesora == 1) {
            return true;
        }
        return false;
    }

}
