/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.sql.Time;
import java.util.Date;

public class AttivitaPeriodica extends Attivita {

    private int id;
    //da decidere se il seguente attributo sarà una stringa
    private String giorno;


    //il costruttore della classe figlia "amplia" quello della classe padre assegnando i valori non inizializzati
    public AttivitaPeriodica(String aula, Time oraInizio, Time oraFine, Docente docente, String ruoloDocente, String categoria, String giorno) {
        super(aula, oraInizio, oraFine, docente, ruoloDocente, categoria);
        this.giorno = giorno;
    }

    public int getId() {
        return id;
    }

    public AttivitaPeriodica setId(int id) {
        this.id = id;
        return this;
    }

    public String getGiorno() {
        return giorno;
    }

    public AttivitaPeriodica setGiorno(String giorno) {
        this.giorno = giorno;
        return this;
    }

    //il seguente è il costruttore che inizializza i dati di deafult
    public AttivitaPeriodica() {}
}
