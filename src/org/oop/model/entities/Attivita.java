/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.sql.Time;
import java.util.Date;

public class Attivita {

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



    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }

    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getRuoloDocente() {
        return ruoloDocente;
    }

    public void setRuoloDocente(String ruoloDocente) {
        this.ruoloDocente = ruoloDocente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
