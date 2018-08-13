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
public class PrichodArcar extends Udalost {

    private int cisloMinibusu;
    private SimJadroPozicovna simJadro;
    private double cas;

    public PrichodArcar(double cas, SimJadroPozicovna simJadro, int cisloMinibusu) {
        super(cas, simJadro);
        this.cisloMinibusu = cisloMinibusu;
        this.simJadro = simJadro;
        this.cas = cas;
    }

    @Override
    public void vykonaj() {
        simJadro.setPoziciuMinibusu(cisloMinibusu, "Spolocnost ARCAR");        
        if (!simJadro.jeMinibusPrazdny(cisloMinibusu)) {
            Minibus minibus = simJadro.getMinibus(cisloMinibusu);
            minibus.setCasZaciatkuUdalosti(cas);
            double trvanie = simJadro.vygenerujVystupZak();
            minibus.setTrvanieVykonu(trvanie);
            minibus.setCinnost("vystup zakaznika");
            simJadro.vlozUdalost(new VystupZak(cas + trvanie, simJadro, cisloMinibusu));
        } else {
            Minibus minibus = simJadro.getMinibus(cisloMinibusu);
            minibus.setCasZaciatkuUdalosti(cas);
            double trvanie = simJadro.vygenerujPrichodAuta1(6.4);
            minibus.setTrvanieVykonu(trvanie);
            minibus.setCinnost("presun do terminalu 1");
            simJadro.vlozUdalost(new PrichodAuta1(cas + trvanie, simJadro, cisloMinibusu));

        }
    }
}
