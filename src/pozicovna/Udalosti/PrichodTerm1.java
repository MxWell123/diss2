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
public class PrichodTerm1 extends Udalost {

    private SimJadroPozicovna simJadro;
    private double cas;

    public PrichodTerm1(double cas, SimJadroPozicovna simJadro) {
        super(cas, simJadro);
        this.simJadro = simJadro;
        this.cas = cas;
    }

    @Override
    public void vykonaj() {
        simJadro.vlozZakaznika(new Zakaznik(cas), 1);
        simJadro.skontrolujZmenu(cas, 1);
        simJadro.vlozUdalost(new PrichodTerm1(cas + simJadro.vygenerujPrichodZakaznika(), simJadro));
    }

}
