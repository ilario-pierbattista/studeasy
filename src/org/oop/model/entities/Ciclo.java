package org.oop.model.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MelvinMancini on 20/12/14.
 */
public class Ciclo {

    /* Dichiarazione degli attributi */
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getInizio() {
        return inizio;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    public Date getFine() {
        return fine;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public ArrayList<Insegnamento> getInsegnamenti() {
        return insegnamenti;
    }

    public void setInsegnamenti(ArrayList<Insegnamento> insegnamenti) {
        this.insegnamenti = insegnamenti;
    }
}
