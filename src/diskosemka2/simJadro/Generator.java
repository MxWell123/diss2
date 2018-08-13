/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskosemka2.simJadro;

import java.util.Random;

/**
 *
 * @author davidecek
 */
public class Generator {

    private Random rnd1;

    public Generator(Long s) {
        rnd1 = new Random(s);
    }

    public Generator() {
    }

    public long generujCislo() {
        Random rnd1 = new Random();
        return rnd1.nextLong();
    }

    public double generujExp(double lambda) {
        return Math.log(1 - rnd1.nextDouble()) / (-lambda);
    }

    public double genPoisson(int pocetZakaznikovZaHod) {
        double pom = (3600 / pocetZakaznikovZaHod);
        double lambda = 1 / pom;
        return generujExp(lambda);
    }

    public double genNastupCestujuceho() {
        return 10 + ((14 - 10) * rnd1.nextDouble());

    }

    public double genVystupCestujuceho() {
        return 4 + ((12 - 4) * rnd1.nextDouble());
    }

    public double genCasObsluhy() {
        return 120 + ((600 - 120) * rnd1.nextDouble());
    }

    public double genCasJazdy(double vzdialenost) {
        return (vzdialenost / 35.00) * 3600;
    }
}
