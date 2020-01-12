package Model;

public class Populacija {
    private int velicinaPopulacije;
    private Hromosom[] populacija;
    private double skorNajboljeg;

    Populacija(int velicinaPopulacije) {
        this.velicinaPopulacije=velicinaPopulacije;
        populacija = new Hromosom[velicinaPopulacije];
        for (int i = 0; i< populacija.length; i++)
            populacija[i]=new Hromosom(true);
    }

    public Hromosom dajNajboljiHromosom() {
        double najboljiFit=Double.MIN_VALUE;
        int indexNajboljeg = 0;
        for (int i=0; i< populacija.length; i++) {
            if (najboljiFit <= populacija[i].fitSkor) {
                najboljiFit = populacija[i].fitSkor;
                indexNajboljeg = i;
            }
        }
        skorNajboljeg = populacija[indexNajboljeg].fitSkor;
        return populacija[indexNajboljeg];
    }

    public Hromosom dajDrugiNajboljiHromosom() {
        int indexNajboljeg=0;
        int indexDrugogNajboljeg=0;
        for (int i=0;i<populacija.length;i++) {
            if (populacija[i].fitSkor > populacija[indexNajboljeg].fitSkor) {
                indexDrugogNajboljeg = indexNajboljeg;
                indexNajboljeg = i;
            }
            else if (populacija[i].fitSkor > populacija[indexDrugogNajboljeg].fitSkor) {
                indexDrugogNajboljeg = i;
            }
        }
        return populacija[indexDrugogNajboljeg];
    }

    public int dajIndexNajgoreg() {
        double skorNajgoreg=Double.MAX_VALUE;
        int indexNajgoreg=0;
        for (int i=0;i<populacija.length; i++) {
            if (skorNajgoreg >= populacija[i].fitSkor) {
                skorNajgoreg = populacija[i].fitSkor;
                indexNajgoreg = i;
            }
        }
        return indexNajgoreg;
    }

    public void izracunarSveFitove() {
        for (int i=0;i<populacija.length;i++) {
            populacija[i].izracunajFit();
        }
        dajNajboljiHromosom();
    }

    public double dajNajboljiSkor() { return skorNajboljeg;}
    public double dajProsjecanFit() {
        double prosjecanSkor= 0;
        for (Hromosom h : populacija) {
            prosjecanSkor+=h.fitSkor;
        }
        prosjecanSkor /= velicinaPopulacije;
        return prosjecanSkor;
    }
    public Hromosom[] dajHromosome() { return populacija; }

}
