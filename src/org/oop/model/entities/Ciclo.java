package org.oop.model.entities;

import java.util.ArrayList;
import java.util.Date;


public class Ciclo {

    /* Dichiarazione degli attributi */
    private int id;
    private String label;
    private Date inizio;
    private Date fine;
    private ArrayList<Insegnamento> insegnamenti;

    /* Metodo Costruttore */
    public Ciclo(String label, Date inizio, Date fine, ArrayList<Insegnamento> insegnamenti) {
        this.label = label;
        this.inizio = inizio;
        this.fine = fine;
        this.insegnamenti = insegnamenti;
    }

    //il seguente è il costruttore che inizializza i dati di deafult
    public Ciclo() {
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

    @Override
    public String toString(){
        return this.label;
    }

    public String toString(boolean debugMode) {
        String str;
        if(debugMode) {
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
