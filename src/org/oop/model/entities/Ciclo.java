package org.oop.model.entities;

import java.util.ArrayList;
import java.util.Date;


/**
 * Classe che descrive un ciclo. Un ciclo è un lasso temporale di durata arbitraria costituito da una lista di
 * insegnamenti. È affine al concetto di semestre dal punto di vista organizzativo, ma più flessibile riguardo la
 * durata.
 */
public class Ciclo {

    private int id;
    private String label;
    private Date inizio;
    private Date fine;
    private ArrayList<Insegnamento> insegnamenti;

    public Ciclo() {
        id = 0;
        insegnamenti = new ArrayList<Insegnamento>(1);
    }

    public int getId() {
        return id;
    }

    public Ciclo setId(int id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Ciclo setLabel(String label) {
        this.label = label;
        return this;
    }

    public Date getInizio() {
        return inizio;
    }

    public Ciclo setInizio(Date inizio) {
        this.inizio = inizio;
        return this;
    }

    public Date getFine() {
        return fine;
    }

    public Ciclo setFine(Date fine) {
        this.fine = fine;
        return this;
    }

    public ArrayList<Insegnamento> getInsegnamenti() {
        return insegnamenti;
    }

    public Ciclo setInsegnamenti(ArrayList<Insegnamento> insegnamenti) {
        this.insegnamenti = insegnamenti;
        return this;
    }

    public Ciclo addInsegnamento(Insegnamento insegnamento) {
        insegnamenti.add(insegnamento);
        return this;
    }

    public Ciclo removeInsegnamento(int id) {
        boolean found = false;
        for (int i = 0; i < insegnamenti.size() && !found; i++) {
            if (id == insegnamenti.get(i).getId()) {
                found = true;
                insegnamenti.remove(i);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public String toString(boolean debugMode) {
        String str;
        if (debugMode) {
            str = "Ciclo{" +
                    "id=" + id +
                    ", label='" + label + '\'' +
                    ", inizio=" + inizio +
                    ", fine=" + fine +
                    ", insegnamenti=" + insegnamenti +
                    '}';
        } else {
            str = label;
        }
        return str;
    }
}
