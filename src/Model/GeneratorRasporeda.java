package Model;

import java.util.Random;

public class GeneratorRasporeda {
    Populacija populacija;
    Hromosom najboljiHromosom;
    Hromosom drugiNajboljiHromosom;
    Hromosom potomakHromosom;
    int velicinaPopulacije;

    private static GeneratorRasporeda instance;
    private GeneratorRasporeda() {}

    public static GeneratorRasporeda getInstance() {
        if (instance == null) {
            instance = new GeneratorRasporeda();
        }
        return instance;
    }

    public void inicijalizacijaPopulacije(int velicinaPopulacije) {
        this.velicinaPopulacije = velicinaPopulacije;
        populacija = new Populacija(velicinaPopulacije);
    }

    public void selekcija() {
        najboljiHromosom = populacija.dajNajboljiHromosom();
        drugiNajboljiHromosom = populacija.dajDrugiNajboljiHromosom();
    }

    public void crossover() {
        Hromosom potomak = new Hromosom(false);
        Hromosom otac = najboljiHromosom;
        Hromosom majka = drugiNajboljiHromosom;
        for ( int i=0; i<najboljiHromosom.duzinaHromosoma; i++) {
            int genOca = otac.getGen(i);
            int genMajke = majka.getGen(i);
            if (genMajke == genOca) {
                if (genOca!=-1)
                    potomak.setGen(i,genOca);
            }
            else if (genOca==-1 && !potomak.daLiJeCasKreiran(genMajke)) {
                potomak.setGen(i,genMajke);
            }
            else if (genMajke==-1 && !potomak.daLiJeCasKreiran(genOca)) {
                potomak.setGen(i,genOca);
            }
            else {
                if(potomak.daLiJeCasKreiran(genMajke) && !potomak.daLiJeCasKreiran(genOca)){
                    potomak.setGen(i,genOca);
                } else if(potomak.daLiJeCasKreiran(genOca) && !potomak.daLiJeCasKreiran(genMajke)){
                    potomak.setGen(i,genMajke);
                } else if(potomak.daLiJeCasKreiran(genOca) && potomak.daLiJeCasKreiran(genMajke)){
                    potomak.setGen(i,-1);
                } else{
                    int motherGeneScore = majka.izracunajSkorGena(genMajke,i);
                    int fatherGeneScore = otac.izracunajSkorGena(genOca,i);
                    if(motherGeneScore > fatherGeneScore)
                        potomak.setGen(i,genMajke);
                    else
                        potomak.setGen(i,genOca);
                }
            }
        }
        for(int i = 0; i< Podaci.casovi.length; i++){
            if(!potomak.daLiJeCasKreiran(Podaci.casovi[i].getID())){
                Random rn = new Random();
                int terminID = rn.nextInt(majka.duzinaHromosoma);
                while(potomak.getGen(terminID)!=-1){
                    terminID = rn.nextInt(majka.duzinaHromosoma);
                }
                potomak.setGen(terminID,Podaci.casovi[i].getID());
            }
        }
        potomakHromosom = potomak;
    }

    public void dodajPotomak() {
        int indexNajgoregHromosoma = populacija.dajIndexNajgoreg();
        populacija.dajHromosome()[indexNajgoregHromosoma]=potomakHromosom;
    }

    public void mutacija(double brzinaMutacije) {
        Random rn = new Random();
        for (int i=0;i<velicinaPopulacije;i++) {
            if (rn.nextInt(10)+1<brzinaMutacije*10) {
                int terminID = rn.nextInt(populacija.dajHromosome()[0].duzinaHromosoma);
                while (populacija.dajHromosome()[i].getGen(terminID)!=-1)
                    terminID = rn.nextInt(populacija.dajHromosome()[0].duzinaHromosoma);
                int casIDx = rn.nextInt(Podaci.casovi.length);
                int casID = Podaci.casovi[casIDx].getID();
                int stariTerminID = populacija.dajHromosome()[i].getTerminID(casID);
                populacija.dajHromosome()[i].setGen(stariTerminID,-1);
                populacija.dajHromosome()[i].setGen(terminID,casID);
            }
        }
    }

    public Populacija getPopulacija() {
        return populacija;
    }
    public Hromosom getNajboljiHromosom() {
        return najboljiHromosom;
    }
}
