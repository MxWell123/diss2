/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna.Udalosti;

import diskosemka2.simJadro.SimulacneJadro;
import diskosemka2.simJadro.Udalost;
import pozicovna.Minibus;
import pozicovna.SimJadroPozicovna;

/**
 *
 * @author davidecek
 */
public class PrichodAuta1 extends Udalost {

    private SimJadroPozicovna simJadro;
    private int cisloMinibusu;
    private double cas;

    public PrichodAuta1(double cas, SimJadroPozicovna simJadro, int cisloMinibusu) {
        super(cas, simJadro);
        this.simJadro = simJadro;
        this.cisloMinibusu = cisloMinibusu;
        this.cas = cas;
    }

    @Override
    public void vykonaj() {
         Minibus minibus = simJadro.getMinibus(cisloMinibusu);
         minibus.setCasZaciatkuUdalosti(cas);
         simJadro.setPoziciuMinibusu(cisloMinibusu, "Terminal 1");
        if (!simJadro.jeRadZakaznikovPrazdny(1)) {   
            double trvanie = simJadro.vygenerujNastupZakaznika1();
            minibus.setTrvanieVykonu(trvanie);
            minibus.setCinnost("nastup zakanika terminal 1");
            simJadro.vlozUdalost(new NastupZakaznika1(cas + trvanie, simJadro, cisloMinibusu));
        } else {
            double trvanie = simJadro.vygenerujPrichodAuta1(0.5);
            minibus.setTrvanieVykonu(trvanie);
            minibus.setCinnost("presun do terminalu 2");
            simJadro.vlozUdalost(new PrichodAuta2(cas + trvanie, simJadro, cisloMinibusu));
        }
    }
}
