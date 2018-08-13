/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna;

/**
 *
 * @author davidecek
 */
public class Statistika {

    private double casZakaznikovVSysteme;
    private double celkCasZakaznikovVSysteme;
    private int pocetZakaznikov;
    private int pravePocetReplik;
    private double casPoslednejZmenyRad1;
    private double casPoslednejZmenyRad2;
    private double casPoslednejZmenyRad3;
    private double dlzkyRadu1;
    private double dlzkyRadu2;
    private double dlzkyRadu3;
    private double zmeny1;
    private double zmeny2;
    private double zmeny3;
    private double casTerminal1;
    private double casTerminal2;
    private double casPredObsluhou;
    private int pocetZakaznikovVRade1;
    private int pocetZakaznikovVRade2;
    private int pocetZakaznikovPredObsluhou;
    private double celkCasTerm1;
    private double celkCasTerm2;
    private double celkCasObsluha;
    private double celkPriemRad1;
    private double celkPriemRad2;
    private double celkPriemRad3;
    private double pocetVytPrac;
    private double casPoslednejZmenyPrac;
    private double celkPocetVytPrac;
    private double zmenyPocPrac;
    private double celkCasZakaznikovVSystemeUmocnene;

    public Statistika() {
        this.casZakaznikovVSysteme = 0.0;
        this.pocetZakaznikov = 0;
        this.celkCasZakaznikovVSysteme = 0.0;
        this.pravePocetReplik = 0;
        this.casPoslednejZmenyRad1 = 0.0;
        this.casPoslednejZmenyRad2 = 0.0;
        this.casPoslednejZmenyRad3 = 0.0;
        this.zmeny1 = 0.0;
        this.zmeny2 = 0.0;
        this.zmeny3 = 0.0;
        this.casTerminal1 = 0.0;
        this.casTerminal2 = 0.0;
        this.casPredObsluhou = 0.0;
        this.pocetZakaznikovVRade1 = 0;
        this.pocetZakaznikovVRade2 = 0;
        this.pocetZakaznikovPredObsluhou = 0;
        this.celkCasTerm1 = 0.0;
        this.celkCasTerm2 = 0.0;
        this.celkCasObsluha = 0.0;
        this.celkPriemRad1 = 0.0;
        this.celkPriemRad2 = 0.0;
        this.celkPriemRad3 = 0.0;
        this.pocetVytPrac = 0.0;
        this.zmenyPocPrac = 0.0;
        this.casPoslednejZmenyPrac = 0.0;
        this.celkPocetVytPrac = 0.0;
        this.celkCasZakaznikovVSystemeUmocnene = 0.0;

    }

    public void pripocitajCasVSysteme(double cas) {
        casZakaznikovVSysteme += cas;
        pocetZakaznikov++;
    }

    public void pripocitajDlzkyRadov(double cas, int rad, int dlzkaRadu) {
        if (rad == 1) {
            dlzkyRadu1 += dlzkaRadu * (cas - casPoslednejZmenyRad1);
            zmeny1 += cas - casPoslednejZmenyRad1;
            casPoslednejZmenyRad1 = cas;
        } else if (rad == 2) {
            dlzkyRadu2 += dlzkaRadu * (cas - casPoslednejZmenyRad2);
            zmeny2 += cas - casPoslednejZmenyRad2;
            casPoslednejZmenyRad2 = cas;
        } else if (rad == 3) {
            dlzkyRadu3 += dlzkaRadu * (cas - casPoslednejZmenyRad3);
            zmeny3 += cas - casPoslednejZmenyRad3;
            casPoslednejZmenyRad3 = cas;
        }
    }

    public void pripocitajVytazenostPracovnikov(double cas, int pocetVytazenychPracovnikov) {
        pocetVytPrac += pocetVytazenychPracovnikov * (cas - casPoslednejZmenyPrac);
        zmenyPocPrac += (cas - casPoslednejZmenyPrac);
        casPoslednejZmenyPrac = cas;
    }

    public void pripocitajCasVSystemeCelkovo() {
        celkCasZakaznikovVSysteme += ((casZakaznikovVSysteme / pocetZakaznikov) / 60);
        celkCasZakaznikovVSystemeUmocnene += Math.pow((casZakaznikovVSysteme / pocetZakaznikov) / 60, 2);
        celkCasTerm1 += (casTerminal1 / pocetZakaznikovVRade1) / 60;
        celkCasTerm2 += (casTerminal2 / pocetZakaznikovVRade2) / 60;
        celkCasObsluha += (casPredObsluhou / pocetZakaznikovPredObsluhou) / 60;
        celkPriemRad1 += dlzkyRadu1 / zmeny1;
        celkPriemRad2 += dlzkyRadu2 / zmeny2;
        celkPriemRad3 += dlzkyRadu3 / zmeny3;
        celkPocetVytPrac += pocetVytPrac / zmenyPocPrac;
        pravePocetReplik++;
    }

    public double[] vypisVysledky() {
        double[] vysledky = new double[8];
        vysledky[0] = (casZakaznikovVSysteme / pocetZakaznikov) / 60;
        vysledky[1] = dlzkyRadu1 / zmeny1;
        vysledky[2] = dlzkyRadu2 / zmeny2;
        vysledky[3] = dlzkyRadu3 / zmeny3;
        vysledky[4] = (casTerminal1 / pocetZakaznikovVRade1) / 60;
        vysledky[5] = (casTerminal2 / pocetZakaznikovVRade2) / 60;
        vysledky[6] = (casPredObsluhou / pocetZakaznikovPredObsluhou) / 60;
        vysledky[7] = (pocetVytPrac / zmenyPocPrac);
        return vysledky;
    }

    public double[] vypisCelkoveVysledky() {
        double[] vysledky = new double[10];
        vysledky[0] = celkCasZakaznikovVSysteme / pravePocetReplik;
        vysledky[1] = celkCasTerm1 / pravePocetReplik;
        vysledky[2] = celkCasTerm2 / pravePocetReplik;
        vysledky[3] = celkCasObsluha / pravePocetReplik;
        vysledky[4] = celkPriemRad1 / pravePocetReplik;
        vysledky[5] = celkPriemRad2 / pravePocetReplik;
        vysledky[6] = celkPriemRad3 / pravePocetReplik;
        vysledky[7] = celkPocetVytPrac / pravePocetReplik;
        vysledky[8] = vypocitajIntSpolahlivosti(pravePocetReplik, 0);
        vysledky[9] = vypocitajIntSpolahlivosti(pravePocetReplik, 1);
        return vysledky;
    }

    public void resetniStatistiky() {
        this.casZakaznikovVSysteme = 0.0;
        this.pocetZakaznikov = 0;
        this.casPoslednejZmenyRad1 = 0.0;
        this.casPoslednejZmenyRad2 = 0.0;
        this.casPoslednejZmenyRad3 = 0.0;
        this.zmeny1 = 0;
        this.zmeny2 = 0;
        this.zmeny3 = 0;
        this.dlzkyRadu1 = 0;
        this.dlzkyRadu2 = 0;
        this.dlzkyRadu3 = 0;
        this.casTerminal1 = 0.0;
        this.casTerminal2 = 0.0;
        this.casPredObsluhou = 0.0;
        this.pocetZakaznikovVRade1 = 0;
        this.pocetZakaznikovVRade2 = 0;
        this.pocetZakaznikovPredObsluhou = 0;
        this.pocetVytPrac = 0;
        this.zmenyPocPrac = 0;
        this.casPoslednejZmenyPrac = 0;

    }

    public void pripocitajCasTerm1(double cas) {
        casTerminal1 += cas;
        pocetZakaznikovVRade1++;
    }

    public void pripocitajCasTerm2(double cas) {
        casTerminal2 += cas;
        pocetZakaznikovVRade2++;
    }

    public void pripocitajCasObsluhaRad(double cas) {
        casPredObsluhou += cas;
        pocetZakaznikovPredObsluhou++;
    }

    private double vypocitajIntSpolahlivosti(int pocetReplikacii, int hranica) {
        double celkovyCasNaDruhu = celkCasZakaznikovVSystemeUmocnene / pocetReplikacii;
        double celkovyCas = celkCasZakaznikovVSysteme / pocetReplikacii;
        double rozptylUmocneny = celkovyCasNaDruhu - Math.pow(celkovyCas, 2);
        double rozptyl = Math.sqrt(rozptylUmocneny);
        double dolnaHranica = celkovyCas - ((1.645 * rozptyl) / Math.sqrt(pocetReplikacii - 1));
        double hornaHranica = celkovyCas + ((1.645 * rozptyl) / Math.sqrt(pocetReplikacii - 1));
        if (hranica == 0) {
            return dolnaHranica;
        } else {
            return hornaHranica;
        }
    }

    public double getDataNaVykreslenie() {
        return (casZakaznikovVSysteme / pocetZakaznikov) / 60;
    }
}
