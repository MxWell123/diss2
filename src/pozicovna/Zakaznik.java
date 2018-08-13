/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna;

/**
 *
 * @author davidecek
 */
public class Zakaznik {

    private double casPrichodu;
    private double casOdchoduZterm;
    private double casPrichoduObsluha;
    private double casOdchoduObsluha;

    public Zakaznik(double casPrichodu) {
        this.casPrichodu = casPrichodu;
        casOdchoduZterm = 0;
        casPrichoduObsluha = 0;
        casOdchoduObsluha = 0;
    }

    public double getCasOdchoduObsluha() {
        return casOdchoduObsluha;
    }

    public void setCasOdchoduObsluha(double casOdchoduObsluha) {
        this.casOdchoduObsluha = casOdchoduObsluha;
    }

    public double getCasOdchoduZterm() {
        return casOdchoduZterm;
    }

    public void setCasOdchoduZterm(double casOdchoduZterm) {
        this.casOdchoduZterm = casOdchoduZterm;
    }

    public double getCasPrichoduObsluha() {
        return casPrichoduObsluha;
    }

    public void setCasPrichoduObsluha(double casPrichoduObsluha) {
        this.casPrichoduObsluha = casPrichoduObsluha;
    }

    public double getCasPrichodu() {
        return casPrichodu;
    }

    public void setCasVRadeTerm(double casOdchoduZterm) {
        this.casOdchoduZterm = casOdchoduZterm;
    }

    public void setCasPrichodu(double casPrichodu) {
        this.casPrichodu = casPrichodu;
    }
}
