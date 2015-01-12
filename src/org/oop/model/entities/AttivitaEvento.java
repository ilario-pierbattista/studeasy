package org.oop.model.entities;

import java.util.Date;


/**
 * La classe descrive una attività senza ripetizione settimanale
 */
public class AttivitaEvento extends Attivita {

    private Date data;

    public AttivitaEvento() {
        super();
    }

    public Date getData() {
        return data;
    }

    public AttivitaEvento setData(Date data) {
        this.data = data;
        return this;
    }
}
