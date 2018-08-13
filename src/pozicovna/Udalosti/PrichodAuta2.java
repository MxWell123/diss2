/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna.Udalosti;

import diskosemka2.simJadro.Udalost;
import pozicovna.Minibus;
import pozicovna.SimJadroPozicovna;

/**
 *
 * @author davidecek
 */
public class PrichodAuta2 extends Udalost {

    private SimJadroPozicovna simJadro;
    private int cisloMinibusu;
    private double cas;

    public PrichodAuta2(double cas, SimJadroPozicovna simJadro, int cisloMinibusu) {
        super(cas, simJadro);
        this.simJadro = simJadro;
        this.cisloMinibusu = cisloMinibusu;
        this.cas = cas;
    }

    @Override
    public void vykonaj() {
        Minibus minibus = simJadro.getMinibus(cisloMinibusu);
        minibus.setCasZaciatkuUdalosti(cas);
        simJadro.setPoziciuMinibusu(cisloMinibusu, "Terminal 2");
        if (!simJadro.jeMinibusPlny(cisloMinibusu) && !simJadro.jeRadZakaznikovPrazdny(2)) {
            double trvanie = simJadro.vygenerujNastupZakaznika1();
            minibus.setTrvanieVykonu(trvanie);
            minibus.setCinnost("nastup zakanika terminal 2");
            simJadro.vlozUdalost(new NastupZakaznika2(cas + trvanie, simJadro, cisloMinibusu));
        } else {
            double trvanie = simJadro.vygenerujPrichodAuta1(2.5);
            minibus.setTrvanieVykonu(trvanie);
            minibus.setCinnost("presun do spolocnosti");
            simJadro.vlozUdalost(new PrichodArcar(cas + trvanie, simJadro, cisloMinibusu));
        }
    }
}
