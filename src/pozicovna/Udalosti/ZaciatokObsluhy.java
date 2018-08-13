/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna.Udalosti;

import diskosemka2.simJadro.SimulacneJadro;
import diskosemka2.simJadro.Udalost;
import pozicovna.SimJadroPozicovna;
import pozicovna.Zakaznik;

/**
 *
 * @author davidecek
 */
public class ZaciatokObsluhy extends Udalost {

    private double cas;
    private SimJadroPozicovna simJadro;
    private Zakaznik zak;

    public ZaciatokObsluhy(double cas, SimJadroPozicovna simJadro, Zakaznik zak) {
        super(cas, simJadro);
        this.cas = cas;
        this.simJadro = simJadro;
        this.zak = zak;
    }

    @Override
    public void vykonaj() {
//        if (!simJadro.isObsluhovany()) {            
            simJadro.pripocitajCasZmenyPracov(cas, simJadro.getPocetPracujicich());            
            simJadro.vlozUdalost(new KoniecObsluhy(cas + simJadro.vygenerujDlzkuObsluhy(), simJadro, zak));
            simJadro.pripocitajCasObsluha(zak.getCasPrichoduObsluha(), cas);
//        } else {
//            simJadro.vlozZakaznika(zak, 3);
//        }
    }

}
