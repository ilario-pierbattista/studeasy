/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.sql.Time;
import java.util.Date;

public class AttivitaEvento extends Attivita {

    private int id;
    private Date data;

    //il costruttore della classe figlia amplia quello della classe padre inizializzando i campi non inizializzati
    public AttivitaEvento(String aula, Time oraInizio, Time oraFine, Docente docente, String ruoloDocente, String categoria, Date data) {
        super(aula, oraInizio, oraFine, docente, ruoloDocente, categoria);
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public AttivitaEvento setId(int id) {
        this.id = id;
        return this;
    }


    //il seguente è il costruttore che inizializza i dati di deafult
    public AttivitaEvento() {}


    public Date getData() {
        return data;
    }

    public AttivitaEvento setData(Date data) {
        this.data = data;
        return this;
    }
}
