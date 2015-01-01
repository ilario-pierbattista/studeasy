package org.oop.model;


import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;

import java.util.ArrayList;

public class Libretto {
    private Corso corso;
    ArrayList<Insegnamento> insegnamenti;

    public Libretto() {
        insegnamenti = new ArrayList<Insegnamento>(20);
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
