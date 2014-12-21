package org.oop.model.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MelvinMancini on 20/12/14.
 */
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
}
