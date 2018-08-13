/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna.Udalosti;

import diskosemka2.simJadro.Udalost;
import pozicovna.Minibus;
import pozicovna.SimJadroPozicovna;
import pozicovna.Zakaznik;

/**
 *
 * @author davidecek
 */
public class NastupZakaznika2 extends Udalost {

    private SimJadroPozicovna simJadro;
    private int cisloMinibusu;
    private double cas;

    public NastupZakaznika2(double cas, SimJadroPozicovna simJadro, int cisloMinibusu) {
        super(cas, simJadro);
        this.simJadro = simJadro;
        this.cisloMinibusu = cisloMinibusu;
        this.cas = cas;
    }

    @Override
    public void vykonaj() {        
        if (!simJadro.jeRadZakaznikovPrazdny(2) && !simJadro.jeMinibusPlny(cisloMinibusu)) {
            Zakaznik zak = simJadro.vyberZakaznika(2);
            simJadro.nastupenieZakaznika(cisloMinibusu, zak);
            simJadro.skontrolujZmenu(cas, 2);
            simJadro.pripocitajCasTerm2(zak.getCasPrichodu(), cas);
            if (!simJadro.jeRadZakaznikovPrazdny(2) && !simJadro.jeMinibusPlny(cisloMinibusu)) {
                Minibus minibus = simJadro.getMinibus(cisloMinibusu);
                minibus.setCasZaciatkuUdalosti(cas);
                double trvanie = simJadro.vygenerujNastupZakaznika1();
                minibus.setTrvanieVykonu(trvanie);
                simJadro.vlozUdalost(new NastupZakaznika2(cas + trvanie, simJadro, cisloMinibusu));
            } else {
                Minibus minibus = simJadro.getMinibus(cisloMinibusu);
                minibus.setCasZaciatkuUdalosti(cas);
                double trvanie = simJadro.vygenerujPrichodAuta1(2.5);
                minibus.setTrvanieVykonu(trvanie);
                minibus.setCinnost("presun do spolocnosti");
                simJadro.vlozUdalost(new PrichodArcar(cas + trvanie, simJadro, cisloMinibusu));
            }
        } else {
            Minibus minibus = simJadro.getMinibus(cisloMinibusu);
            minibus.setCasZaciatkuUdalosti(cas);
            double trvanie = simJadro.vygenerujPrichodAuta1(2.5);
            minibus.setTrvanieVykonu(trvanie);
            minibus.setCinnost("presun do spolocnosti");
            simJadro.vlozUdalost(new PrichodArcar(cas + trvanie, simJadro, cisloMinibusu));
        }
    }
}
