package org.oop.model.entities;

import java.sql.Time;
import java.util.Date;

public class AttivitaEvento extends Attivita {

    private Date data;

    public AttivitaEvento(String aula, Time oraInizio, Time oraFine, Docente docente, String ruoloDocente, String categoria, Date data) {
        super(aula, oraInizio, oraFine, docente, ruoloDocente, categoria);
        this.data = data;
    }

    public AttivitaEvento() {}


    public Date getData() {
        return data;
    }

    public AttivitaEvento setData(Date data) {
        this.data = data;
        return this;
    }
}
