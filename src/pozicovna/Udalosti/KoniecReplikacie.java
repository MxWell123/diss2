/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna.Udalosti;

import diskosemka2.simJadro.Udalost;
import pozicovna.SimJadroPozicovna;

/**
 *
 * @author davidecek
 */
public class KoniecReplikacie extends Udalost {

    private SimJadroPozicovna simJadro;
    private boolean graf;

    public KoniecReplikacie(double cas, SimJadroPozicovna simJadro, boolean graf) {
        super(cas, simJadro);
        this.simJadro = simJadro;
        this.graf = graf;
    }

    @Override
    public void vykonaj() {
        simJadro.pripocitajCasStravenyVSystemeCelkovo();
        if (!graf) {
            simJadro.refreshGUI();
        }        
        simJadro.reset();        
    }

}
