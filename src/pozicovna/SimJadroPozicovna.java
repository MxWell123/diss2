/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozicovna;

import diskosemka2.simJadro.Generator;
import diskosemka2.simJadro.Obs;
import diskosemka2.simJadro.SimulacneJadro;
import diskosemka2.simJadro.Udalost;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import pozicovna.Udalosti.KoniecReplikacie;
import pozicovna.Udalosti.PrichodAuta1;
import pozicovna.Udalosti.PrichodTerm1;
import pozicovna.Udalosti.PrichodTerm2;

/**
 *
 * @author davidecek
 */
public class SimJadroPozicovna extends SimulacneJadro {

    private PriorityQueue<Zakaznik> radZakaznikovTerm1;
    private PriorityQueue<Zakaznik> radZakaznikovTerm2;
    private PriorityQueue<Zakaznik> radZakaznikovObsluha;
    private Generator genNasad;
    private Generator gen1;
    private Generator gen2;
    private Generator gen3;
    private Generator gen4;
    private Generator gen5;
    private Generator gen6;
    private Statistika stat;
    private int dlzkaRadu1;
    private int dlzkaRadu2;
    private int dlzkaRadu3;
    private Minibus[] minibusy;
    private int pocetPracovnikov;
    private int pocetObsluhovanych;
    private int pocetReplikacii;
    private double pom;
    private int counter;
    private int pocetMinibusov;
    private double maxCas;
    private int pocetPracujicich;
    private int spomalenie;
    private boolean graf;

    public SimJadroPozicovna(int pocetReplikacii, int spomalenie, double maxCas, int pocetMiniBusov, int pocetPracovnikov, boolean graf) {
        super(pocetReplikacii, spomalenie, maxCas);
        this.radZakaznikovTerm1 = new PriorityQueue(Comparator.comparingDouble(Zakaznik::getCasPrichodu));
        this.radZakaznikovTerm2 = new PriorityQueue(Comparator.comparingDouble(Zakaznik::getCasPrichodu));
        this.radZakaznikovObsluha = new PriorityQueue(Comparator.comparingDouble(Zakaznik::getCasPrichodu));
        genNasad = new Generator();
        gen1 = new Generator(genNasad.generujCislo());
        gen2 = new Generator(genNasad.generujCislo());
        gen3 = new Generator(genNasad.generujCislo());
        gen4 = new Generator(genNasad.generujCislo());
        gen5 = new Generator(genNasad.generujCislo());
        gen6 = new Generator(genNasad.generujCislo());
        this.maxCas = maxCas;
        pom = 0.0;
        this.spomalenie = spomalenie;
        this.pocetMinibusov = pocetMiniBusov;
        this.pocetReplikacii = pocetReplikacii;
        stat = new Statistika();
        this.dlzkaRadu1 = 0;
        this.dlzkaRadu2 = 0;
        this.dlzkaRadu3 = 0;
        this.graf = graf;
        this.pocetPracovnikov = pocetPracovnikov;
        this.pocetObsluhovanych = 0;
        this.pocetPracujicich = 0;
        minibusy = new Minibus[pocetMiniBusov];
        for (int i = 0; i < pocetMiniBusov; i++) {
            minibusy[i] = new Minibus(12);
        }
        PrichodTerm1 prichod1 = new PrichodTerm1(0, this);
        PrichodTerm2 prichod2 = new PrichodTerm2(0, this);
        this.vlozUdalost(prichod1);
        this.vlozUdalost(prichod2);
        for (int i = 0; i < pocetMiniBusov; i++) {
            PrichodAuta1 prichodAuta = new PrichodAuta1(20 * 60.0 * i, this, i);
            this.vlozUdalost(prichodAuta);
        }
        counter = 1;

        this.vlozUdalost(new KoniecReplikacie(counter * maxCas, this, graf));

    }

    public int getPocetPracovnikov() {
        return pocetPracovnikov;
    }

    public void setSpomalenie(int spomalenie) {
        super.setSpomalenie(spomalenie);
    }

    public PriorityQueue<Zakaznik> getRadZakaznikovObsluha() {
        return radZakaznikovObsluha;
    }

    public void skontrolujZmenu(double cas, int rad) {
        if (rad == 1) {
            if (dlzkaRadu1 != radZakaznikovTerm1.size()) {
                dlzkaRadu1 = radZakaznikovTerm1.size();
                stat.pripocitajDlzkyRadov(cas, rad, dlzkaRadu1);
            }
        } else if (rad == 2) {
            if (dlzkaRadu2 != radZakaznikovTerm2.size()) {
                dlzkaRadu2 = radZakaznikovTerm1.size();
                stat.pripocitajDlzkyRadov(cas, rad, dlzkaRadu2);
            }
        } else {
            if (dlzkaRadu3 != radZakaznikovObsluha.size()) {
                dlzkaRadu3 = radZakaznikovObsluha.size();
                stat.pripocitajDlzkyRadov(cas, rad, dlzkaRadu3);
            }
        }
    }

    public void pripocitajCasZmenyPracov(double cas, int pocetVytazenychPrac) {
        stat.pripocitajVytazenostPracovnikov(cas, pocetVytazenychPrac);
    }

    public void pripocitajCasStravenyVSysteme(double cas) {
        stat.pripocitajCasVSysteme(cas);
    }

    public void pripocitajCasTerm1(double casPrichodu, double casOdchodu) {
        double cas = casOdchodu - casPrichodu;
        stat.pripocitajCasTerm1(cas);
    }

    public void pripocitajCasTerm2(double casPrichodu, double casOdchodu) {
        double cas = casOdchodu - casPrichodu;
        stat.pripocitajCasTerm2(cas);
    }

    public void pripocitajCasObsluha(double casPrichodu, double casOdchodu) {
        double cas = casOdchodu - casPrichodu;
        stat.pripocitajCasObsluhaRad(cas);
    }

    public void pripocitajCasStravenyVSystemeCelkovo() {
        stat.pripocitajCasVSystemeCelkovo();
    }

    public boolean jeRadZakaznikovPrazdny(int cisloRadu) {
        if (cisloRadu == 1) {
            if (radZakaznikovTerm1.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else if (cisloRadu == 2) {
            if (radZakaznikovTerm2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else if (radZakaznikovObsluha.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean jeMinibusPlny(int cisloMinibusu) {
        if (minibusy[cisloMinibusu].plny()) {
            return true;
        }
        return false;
    }

    public int getPocetPracujicich() {
        return pocetPracujicich;
    }

    public void setPocetPracujicich(int pocetPracujicich) {
        this.pocetPracujicich = pocetPracujicich;
    }

    public boolean jeMinibusPrazdny(int cisloMinibusu) {
        if (minibusy[cisloMinibusu].prazdny()) {
            return true;
        }
        return false;
    }

    public Zakaznik vyberZakaznika(int cisloRadu) {
        if (cisloRadu == 1) {
            return radZakaznikovTerm1.poll();
        } else if (cisloRadu == 2) {
            return radZakaznikovTerm2.poll();
        } else {
            return radZakaznikovObsluha.poll();
        }
    }

    public void vlozZakaznika(Zakaznik zakaznik, int cisloRadu) {
        if (cisloRadu == 1) {
            radZakaznikovTerm1.add(zakaznik);
        } else if (cisloRadu == 2) {
            radZakaznikovTerm2.add(zakaznik);
        } else {
            radZakaznikovObsluha.add(zakaznik);
        }
    }

    public boolean isObsluhovany() {
        if (pocetObsluhovanych >= pocetPracovnikov) {
            return true;
        } else {
            return false;
        }
    }

    public int getPocetObsluhovanych() {
        return pocetObsluhovanych;
    }

    public void pripocitajObsadenychPracovnikov() {
        pocetObsluhovanych++;
    }

    public void odpocitajObsadenychPracovnikov() {
        pocetObsluhovanych--;
    }

    @Override
    public void vlozUdalost(Udalost udalost) {
        super.vlozUdalost(udalost);
    }

    public double vygenerujPrichodZakaznika() {
        return gen1.genPoisson(43);
    }

    public double vygenerujPrichodZakaznika2() {
        return gen2.genPoisson(19);
    }

    public double vygenerujNastupZakaznika1() {
        return gen3.genNastupCestujuceho();
    }

    public double vygenerujPrichodAuta1(double vzdialenost) {
        return gen4.genCasJazdy(vzdialenost);
    }

    public double vygenerujVystupZak() {
        return gen5.genVystupCestujuceho();
    }

    public double vygenerujDlzkuObsluhy() {
        return gen6.genCasObsluhy();
    }

    public void nastupenieZakaznika(int cisloMinibusu, Zakaznik zak) {
        minibusy[cisloMinibusu].nastupZak(zak);
    }

    public Zakaznik vyberZakaznikaZMinibusu(int cisloMinibusu) {
        return minibusy[cisloMinibusu].vystupZak();
    }

    public void vypisVysledky() {
        stat.vypisVysledky();
    }

    public double[] getCelkoveVysledky() {
        return stat.vypisCelkoveVysledky();
    }

    public Minibus[] getMinibusy() {
        return minibusy;
    }

    public Minibus getMinibus(int cisloMinibusu) {
        return minibusy[cisloMinibusu];
    }

    public double vypocitaPoziciuMinibusu(int cisloMinibusu) {
        return minibusy[cisloMinibusu].vypocitajPoziciu(this.getAkt_SimCas());
    }

    public double[] getPriebezneVysledky() {
        if (super.getAkt_SimCas() > 55 * 60 * 60) {
            return stat.vypisVysledky();
        } else {
            double[] vysledok = new double[8];
            for (int i = 0; i < 8; i++) {
                vysledok[i] = 0.0;
            }
            return vysledok;
        }
    }

    @Override
    public void pozastav() {
        super.pozastav();
    }

    @Override
    public void ukonciSimulaciu() {
        stat.pripocitajCasVSystemeCelkovo();
        stat.vypisCelkoveVysledky();
        super.ukonciSimulaciu();
    }

    public int getPocetMinibusov() {
        return pocetMinibusov;
    }

    public void setPocetMinibusov(int pocetMinibusov) {
        this.pocetMinibusov = pocetMinibusov;
    }

    public void reset() {
        if (counter < pocetReplikacii) {
            super.clearCasovaOs();
            super.setAkt_SimCas(0);
            stat.resetniStatistiky();
            this.radZakaznikovTerm1.clear();
            this.radZakaznikovTerm2.clear();
            this.radZakaznikovObsluha.clear();
            for (int i = 0; i < minibusy.length; i++) {
                minibusy[i] = new Minibus(12);
            }
            dlzkaRadu1 = 0;
            dlzkaRadu2 = 0;
            dlzkaRadu3 = 0;
            pocetObsluhovanych = 0;
            PrichodTerm1 prichod1 = new PrichodTerm1(0, this);
            PrichodTerm2 prichod2 = new PrichodTerm2(0, this);
            this.vlozUdalost(prichod1);
            this.vlozUdalost(prichod2);
            for (int i = 0; i < pocetMinibusov; i++) {
                PrichodAuta1 prichodAuta = new PrichodAuta1(10 * 60.0 * i, this, i);
                this.vlozUdalost(prichodAuta);
            }
            this.vlozUdalost(new KoniecReplikacie(maxCas, this, graf));
            counter++;
        } else {
            super.setAkt_SimCas(3000000);
        }

    }

    public void setPoziciuMinibusu(int cisloMinibusu, String terminal_1) {
        minibusy[cisloMinibusu].setPoslednaUdalost(terminal_1);
    }

    public double getDataNaVykreslenie() {
        return stat.getDataNaVykreslenie();
    }

    public void setStop() {
        super.setUkonci();
    }

}
