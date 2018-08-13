/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna;

import diskosemka2.simJadro.Udalost;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author davidecek
 */
public class Minibus {

    private int pocetSedadiel;
    private PriorityQueue<Zakaznik> minibus;
    private int zaplnene;
    private String poslednaUdalost;
    private String cinnost;
    private double casZaciatkuUdalosti;
    private double trvanieVykonu;

    public Minibus(int pocetSedadiel) {
        this.pocetSedadiel = pocetSedadiel;
        minibus = new PriorityQueue(Comparator.comparingDouble(Zakaznik::getCasPrichodu));
        zaplnene = 0;
        this.poslednaUdalost = "";
        this.cinnost = "";
        this.casZaciatkuUdalosti = 0.0;
        this.trvanieVykonu = 0.0;
    }

    public void nastupZak(Zakaznik zakaznik) {
        if (zaplnene < pocetSedadiel) {
            minibus.add(zakaznik);
            zaplnene++;
        }
    }

    public String getCinnost() {
        return cinnost;
    }

    public void setCinnost(String cinnost) {
        this.cinnost = cinnost;
    }

    public double vypocitajPoziciu(double aktualnyCas) {             
        return Math.round(((aktualnyCas - casZaciatkuUdalosti)
                / ((casZaciatkuUdalosti + trvanieVykonu) - casZaciatkuUdalosti)) * 100);
    }

    public String getPoslednaUdalost() {
        return poslednaUdalost;
    }

    public void setPoslednaUdalost(String poslednaUdalost) {
        this.poslednaUdalost = poslednaUdalost;
    }

    public void setCasZaciatkuUdalosti(double casZaciatkuUdalosti) {
        this.casZaciatkuUdalosti = casZaciatkuUdalosti;
    }

    public void setTrvanieVykonu(double trvanieVykonu) {
        this.trvanieVykonu = trvanieVykonu;
    }

    public Zakaznik vystupZak() {
        if (zaplnene > 0) {
            zaplnene--;
            return minibus.poll();
        }
        return null;
    }

    public boolean plny() {
        if (zaplnene == pocetSedadiel) {
            return true;
        }
        return false;
    }

    public boolean prazdny() {
        if (zaplnene == 0) {
            return true;
        }
        return false;
    }

    public int getZaplnene() {
        return zaplnene;
    }

}
