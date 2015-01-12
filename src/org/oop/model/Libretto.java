package org.oop.model;


import org.oop.general.Utils;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.InsegnamentoOfferto;

import java.util.ArrayList;


/**
 * Rappresenta i dati di un libretto. Fa anche le veci del piano di studi.
 */
public class Libretto {
    ArrayList<Insegnamento> insegnamenti;
    private Corso corso;

    public Libretto() {
        insegnamenti = new ArrayList<Insegnamento>(20);
    }

    /**
     * Calcola i CFU conseguiti
     *
     * @return Somma dei CFU degli esami superati
     */
    public int calcolaCFU() {
        int cfu = 0;
        for (Insegnamento insegnamento : insegnamenti) {
            if (insegnamento.esameSostenuto()) {
                cfu += insegnamento.getInsegnamentoOfferto().getCfu();
            }
        }
        return cfu;
    }

    /**
     * Calcola i cfu previsti in base agli insegnamenti opzionati
     *
     * @return Cfu previsti
     */
    public int calcolaCFUPrevisti() {
        int cfu = 0;
        for (Insegnamento insegnamento : insegnamenti) {
            cfu += insegnamento.getInsegnamentoOfferto().getCfu();
        }
        return cfu;
    }

    /**
     * Calcola la media aritmetica dei voti degli esami sostenuti
     *
     * @return Media aritmetica degli insegnamenti superati
     */
    public double calcolaMediaAritmetica() {
        int count = 0;
        double sum = 0;
        for (Insegnamento insegnamento : getInsegnamenti()) {
            if (insegnamento.esameSostenuto()) {
                count++;
                sum += insegnamento.getVoto();
            }
        }
        return Utils.round(sum / count, 2);
    }

    /**
     * Calcola la media ponderata dei voti degli esami sostenuti
     *
     * @return Media ponderata
     */
    public double calcolaMediaPonderata() {
        int cfu = 0;
        double sommaPesata = 0;
        for (Insegnamento insegnamento : getInsegnamenti()) {
            if (insegnamento.esameSostenuto()) {
                cfu += insegnamento.getInsegnamentoOfferto().getCfu();
                sommaPesata += (insegnamento.getVoto() * insegnamento.getInsegnamentoOfferto().getCfu());
            }
        }
        return Utils.round(sommaPesata / cfu, 2);
    }

    /**
     * Calcola il numero di insegnamenti superati in un anno accademico
     *
     * @param annoAccademico Anno accademico
     * @return Numero di esami superati
     */
    public int calcolaEsamiSuperatiPerAnnoAccademico(int annoAccademico) {
        int counter = 0;
        for (Insegnamento insegnamento : insegnamenti) {
            if (insegnamento.getAnnoAccademico() == annoAccademico) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Calcola la somma dei CFU conseguiti in uno specifico anno accademico
     *
     * @param annoAccademico Anno accademico
     * @return CFU conseguiti
     */
    public int calcolaCFUConseguitiPerAnnoAccademico(int annoAccademico) {
        int cfu = 0;
        for (Insegnamento insegnamento : insegnamenti) {
            if (insegnamento.getAnnoAccademico() == annoAccademico) {
                cfu += insegnamento.getInsegnamentoOfferto().getCfu();
            }
        }
        return cfu;
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

    /**
     * Ritorna solamente gli insegnamenti opzionali
     *
     * @return Insegnamenti opzionali
     */
    public ArrayList<Insegnamento> getInsegnamentiOpzionali() {
        ArrayList<Insegnamento> opzionali = new ArrayList<Insegnamento>(5);
        for (Insegnamento insegnamento : insegnamenti) {
            if (insegnamento.getInsegnamentoOfferto().isOpzionale()) {
                opzionali.add(insegnamento);
            }
        }
        return opzionali;
    }

    /**
     * Ritorna gli insegnamenti obbligatori
     *
     * @return Insegnamenti obbligatori
     */
    public ArrayList<Insegnamento> getInsegnamentiObbligatori() {
        ArrayList<Insegnamento> obbligatori = new ArrayList<Insegnamento>(15);
        for (Insegnamento insegnamento : insegnamenti) {
            if (!insegnamento.getInsegnamentoOfferto().isOpzionale()) {
                obbligatori.add(insegnamento);
            }
        }
        return obbligatori;
    }

    public Libretto addInsegnamento(Insegnamento insegnamento) {
        insegnamenti.add(insegnamento);
        return this;
    }

    public boolean hasInsegnamentoOfferto(InsegnamentoOfferto insegnamentoOfferto) {
        boolean found = false;
        for (int i = 0; i < insegnamenti.size() && !found; i++) {
            found = insegnamenti.get(i).getInsegnamentoOfferto().getId() == insegnamentoOfferto.getId();
        }
        return found;
    }
}
