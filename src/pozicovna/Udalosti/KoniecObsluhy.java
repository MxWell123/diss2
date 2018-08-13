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
public class KoniecObsluhy extends Udalost {

    private double cas;
    private SimJadroPozicovna simJadro;
    private Zakaznik zak;

    public KoniecObsluhy(double cas, SimJadroPozicovna simJadro, Zakaznik zak) {
        super(cas, simJadro);
        this.cas = cas;
        this.simJadro = simJadro;
        this.zak = zak;
    }

    @Override
    public void vykonaj() {

        if (cas > 48 * 60 * 60) {
            simJadro.pripocitajCasStravenyVSysteme(cas - zak.getCasPrichodu());
        }
        if (!simJadro.jeRadZakaznikovPrazdny(3)) {
            simJadro.vlozUdalost(new ZaciatokObsluhy(cas, simJadro, simJadro.vyberZakaznika(3)));
            simJadro.skontrolujZmenu(cas, 3);
        } else {
            simJadro.pripocitajCasZmenyPracov(cas, simJadro.getPocetObsluhovanych());
            simJadro.odpocitajObsadenychPracovnikov();
        }

    }

}
