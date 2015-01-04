package org.oop.model.entities;

import java.sql.Time;

public class AttivitaPeriodica extends Attivita {

    /**
     * Rappresenta un giorno della settimana. Assume uno dei valori possibili per
     * DAY_OF_WEEK di java.util.Calendar
     */
    private int giorno;

    //il costruttore della classe figlia "amplia" quello della classe padre assegnando i valori non inizializzati
    public AttivitaPeriodica(String aula, Time oraInizio, Time oraFine, Docente docente, String ruoloDocente, String categoria, int giorno) {
        super(aula, oraInizio, oraFine, docente, ruoloDocente, categoria);
        this.giorno = giorno;
    }

    public int getGiorno() {
        return giorno;
    }

    public AttivitaPeriodica setGiorno(int giorno) {
        this.giorno = giorno;
        return this;
    }

    //il seguente è il costruttore che inizializza i dati di deafult
    public AttivitaPeriodica() {}
}
