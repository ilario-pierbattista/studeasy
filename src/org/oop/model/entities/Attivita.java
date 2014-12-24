/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.sql.Time;
import java.util.Date;

public class Attivita {

    private int id;
    private String aula;
    private Time oraInizio;
    private Time oraFine;
    private Docente docente;
    //da decidere se i seguenti attributi saranno o meno stringhe
    private String ruoloDocente;
    private String categoria;


    //per il costruttore è stato usato il this per evitare confusione nella lettura del codice dato l'elevato numero di parametri
    public Attivita(String aula, Time oraInizio, Time oraFine, Docente docente, String ruoloDocente, String categoria) {
        this.aula = aula;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.docente = docente;
        this.ruoloDocente = ruoloDocente;
        this.categoria = categoria;
    }

<<<<<<< HEAD
=======
    //il seguente è il costruttore che inizializza i dati di deafult
    public Attivita() {}


>>>>>>> de08e09c33d6de3fed78ed1ed40f69777d479f50
    public int getId() {
        return id;
    }

    public Attivita setId(int id) {
        this.id = id;
        return this;
    }

    public String getAula() {
        return aula;
    }

    public Attivita setAula(String aula) {
        this.aula = aula;
        return this;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public Attivita setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
        return this;
    }

    public Time getOraFine() {
        return oraFine;
    }

    public Attivita setOraFine(Time oraFine) {
        this.oraFine = oraFine;
        return this;
    }

    public Docente getDocente() {
        return docente;
    }

    public Attivita setDocente(Docente docente) {
        this.docente = docente;
        return this;
    }

    public String getRuoloDocente() {
        return ruoloDocente;
    }

    public Attivita setRuoloDocente(String ruoloDocente) {
        this.ruoloDocente = ruoloDocente;
        return this;
    }

    public String getCategoria() {
        return categoria;
    }

    public Attivita setCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }
}
