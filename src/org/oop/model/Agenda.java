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

    public Agenda removeCiclo(Ciclo ciclo) {
        boolean found = false;
        for (int i = 0; i < cicli.size() && !found; i++) {
            if(ciclo.getId() == cicli.get(i).getId()) {
                found = true;
                cicli.remove(i);
            }
        }
        return this;
    }

    public Ciclo findCiclo(int idciclo){
        boolean found = false;
        Ciclo c = new Ciclo();
        for (int i = 0; i < cicli.size() && !found; i++) {
            if(idciclo == cicli.get(i).getId()) {
                found = true;
                c = cicli.get(i);
            }
        }

        return c;
    }
}
