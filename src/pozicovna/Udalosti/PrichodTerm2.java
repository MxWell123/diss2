/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna.Udalosti;

import diskosemka2.simJadro.Udalost;
import pozicovna.SimJadroPozicovna;
import pozicovna.Zakaznik;

/**
 *
 * @author davidecek
 */
public class PrichodTerm2 extends Udalost {

    private SimJadroPozicovna simJadro;
    private double cas;

    public PrichodTerm2(double cas, SimJadroPozicovna simJadro) {
        super(cas, simJadro);
        this.simJadro = simJadro;
        this.cas = cas;
    }

    @Override
    public void vykonaj() {
        simJadro.vlozZakaznika(new Zakaznik(cas), 2);
        simJadro.skontrolujZmenu(cas, 2);
        simJadro.vlozUdalost(new PrichodTerm2(cas + simJadro.vygenerujPrichodZakaznika2(), simJadro));
    }
}
