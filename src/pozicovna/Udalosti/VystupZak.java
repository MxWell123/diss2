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
import pozicovna.Zakaznik;

/**
 *
 * @author davidecek
 */
public class VystupZak extends Udalost {

    private double cas;
    private SimJadroPozicovna simJadro;
    private int cisloMinibusu;

    public VystupZak(double cas, SimJadroPozicovna simJadro, int cisloMinibusu) {
        super(cas, simJadro);
        this.cas = cas;
        this.simJadro = simJadro;
        this.cisloMinibusu = cisloMinibusu;
    }

    @Override
    public void vykonaj() {
        Zakaznik zak = simJadro.vyberZakaznikaZMinibusu(cisloMinibusu);
        if (!simJadro.isObsluhovany()) {
            simJadro.setPocetPracujicich(simJadro.getPocetObsluhovanych());
            simJadro.pripocitajObsadenychPracovnikov();
            simJadro.vlozUdalost(new ZaciatokObsluhy(cas, simJadro, zak));
            zak.setCasPrichoduObsluha(cas);
        } else {
            simJadro.vlozZakaznika(zak, 3);// inak vloz do radu 
            zak.setCasPrichoduObsluha(cas);
            simJadro.skontrolujZmenu(cas, 3);
        }
        if (!simJadro.jeMinibusPrazdny(cisloMinibusu)) { // ak stale nie je minibus prazdny tak dalsi vystup
            Minibus minibus = simJadro.getMinibus(cisloMinibusu);
            minibus.setCasZaciatkuUdalosti(cas);
            double trvanie = simJadro.vygenerujVystupZak();
            minibus.setTrvanieVykonu(trvanie);
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
