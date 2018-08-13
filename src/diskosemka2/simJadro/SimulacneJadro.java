/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskosemka2.simJadro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import javax.swing.SwingWorker;

/**
 *
 * @author davidecek
 */
public class SimulacneJadro {

    private int pocetReplikacii;
    private boolean ukonci;
    private boolean ukoncenieSimulacie;
    private double akt_SimCas;
    private double maxCas;
    private PriorityQueue<Udalost> casovaOs;
    private List<Obs> delegates;
    private Udalost pomUdalost;
    private int spomalenie;

    public SimulacneJadro(int pocetReplikacii, int spomalenie, double maxCas) {
        this.pocetReplikacii = pocetReplikacii;
        ukonci = false;
        ukoncenieSimulacie = false;
        this.maxCas = maxCas;
        akt_SimCas = 0.0;
        this.spomalenie = spomalenie;
        pomUdalost = new Udalost(0, this);
        casovaOs = new PriorityQueue<>(Comparator.comparingDouble(Udalost::getCas));
        delegates = new ArrayList<>();
    }

    public void spusti() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (akt_SimCas <= maxCas && !casovaOs.isEmpty()) {
                    pomUdalost = casovaOs.poll();
                    akt_SimCas = pomUdalost.getCas();
                    pomUdalost.vykonaj();
                    if (pocetReplikacii == 1) {
                        vypisPriebezneVysledky();
                    }
                    Thread.sleep(spomalenie * 1);
                    if (ukonci) {
                        ukonciSimulaciu();
                    }
                    while (ukonci) {
                        Thread.sleep(10);
                    }

                }
                return null;
            }

            @Override
            protected void done() {
                refreshGUI();
            }

        };
        worker.execute();
    }

    public void ukonciSimulaciu() {
        refreshGUI();
    }

    public void vypisPriebezneVysledky() {
        refreshGUI();
    }

    public void setAkt_SimCas(double akt_SimCas) {
        this.akt_SimCas = akt_SimCas;
    }

    public void pozastav() {
        if (ukonci) {
            ukonci = false;
        } else {
            ukonci = true;
        }
    }

    public void vlozUdalost(Udalost udalost) {
        if (udalost.getCas() <= maxCas) {
            casovaOs.add(udalost);
        }

    }

    public void registerDelegate(Obs delegate) {
        delegates.add(delegate);
    }

    public void refreshGUI() {
        for (Obs delegate : delegates) {
            delegate.refresh(this);
        }
    }

    public double getAkt_SimCas() {
        return akt_SimCas;
    }

    public void clearCasovaOs() {
        casovaOs.clear();
    }

    public void setSpomalenie(int spomalenie) {
        this.spomalenie = spomalenie;
    }

    public void setUkonci() {
        ukonci = true;
    }
}
