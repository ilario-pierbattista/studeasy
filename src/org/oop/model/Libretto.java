package org.oop.model;


import org.oop.general.Utils;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;

import java.util.ArrayList;

public class Libretto {
    private Corso corso;
    ArrayList<Insegnamento> insegnamenti;

    public Libretto() {
        insegnamenti = new ArrayList<Insegnamento>(20);
    }

    /**
     * Calcola i CFU conseguiti
     * @return Somma dei CFU degli esami superati
     */
    public int calcolaCFU() {
        int cfu = 0;
        for (Insegnamento insegnamento : insegnamenti) {
            if(insegnamento.esameSostenuto()) {
                cfu += insegnamento.getInsegnamentoOfferto().getCfu();
            }
        }
        return cfu;
    }

    public int calcolaCFUPrevisti() {
        int cfu = 0;
        for(Insegnamento insegnamento : insegnamenti) {
            cfu += insegnamento.getInsegnamentoOfferto().getCfu();
        }
        return cfu;
    }

    /**
     * Calcola la media aritmetica dei voti degli esami sostenuti
     * @return
     */
    public double calcolaMediaAritmetica() {
        int count = 0;
        double sum = 0;
        for (Insegnamento insegnamento : getInsegnamenti()) {
            if(insegnamento.esameSostenuto()) {
                count++;
                sum += insegnamento.getVoto();
            }
        }
        return Utils.round(sum / count, 2);
    }

    /**
     * Calcola la media ponderata dei voti degli esami sostenuti
     * @return
     */
    public double calcolaMediaPonderata() {
        int cfu = 0;
        double sommaPesata = 0;
        for (Insegnamento insegnamento : getInsegnamenti()) {
            if(insegnamento.esameSostenuto()) {
                cfu += insegnamento.getInsegnamentoOfferto().getCfu();
                sommaPesata += (insegnamento.getVoto() * insegnamento.getInsegnamentoOfferto().getCfu());
            }
        }
        return Utils.round(sommaPesata / cfu, 2);
    }

    public Corso getCorso() {
        return corso;
    }

    public Libretto setCorso(Corso corso) {
        this.corso = corso;
        return this;
    }

    public ArrayList<Insegnamento> getInsegnamenti() {
        return insegnamenti;
    }

    public Libretto setInsegnamenti(ArrayList<Insegnamento> insegnamenti) {
        this.insegnamenti = insegnamenti;
        return this;
    }

    public Libretto addInsegnamento(Insegnamento insegnamento) {
        insegnamenti.add(insegnamento);
        return this;
    }

    public boolean hasInsegnamento(Insegnamento insegnamento) {
        boolean found = false;
        for (int i = 0; i < insegnamenti.size() && !found; i++) {
            found = insegnamenti.get(i).equals(insegnamento);
        }
        return found;
    }
}
