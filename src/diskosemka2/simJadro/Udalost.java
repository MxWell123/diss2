/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskosemka2.simJadro;

/**
 *
 * @author davidecek
 */
public class Udalost {
    
    private final double cas;
    private final SimulacneJadro simJadro;

    

    public Udalost(double cas, SimulacneJadro simJadro) {
        this.cas = cas;
        this.simJadro = simJadro;
    }

    public double getCas() {
        return cas;
    }

    public void vykonaj() {
    }
}
