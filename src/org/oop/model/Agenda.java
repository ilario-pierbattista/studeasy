package org.oop.model;


import org.oop.model.entities.Ciclo;

import java.util.ArrayList;

public class Agenda {
    ArrayList<Ciclo> cicli;

    public Agenda() {
        cicli = new ArrayList<Ciclo>(3);
    }

    public ArrayList<Ciclo> getCicli() {
        return cicli;
    }

    public Agenda setCicli(ArrayList<Ciclo> cicli) {
        this.cicli = cicli;
        return this;
    }

    public Agenda addCiclo(Ciclo ciclo) {
        cicli.add(ciclo);
        return this;
    }
}
