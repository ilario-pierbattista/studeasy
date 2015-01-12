package org.oop.model.entities;

import java.time.LocalTime;


/**
 * La classe descrive un'attività.
 */
public class Attivita {
    /**
     * Valori assumibili dall'attributo categoria
     */
    public static final String LEZIONE = "lezione";
    public static final String LABORATORIO = "laboratorio";
    public static final String PROGETTO = "progetto";
    public static final String ESAME = "esame";
    public static final String SEMINARIO = "seminario";

    /**
     * Valori assumibili dall'attributo ruoloDocente
     */
    public static final String DOCENTE = "docente";
    public static final String ASSISTENTE = "assistente";
    public static final String TUTOR = "tutor";

    protected int id;
    private String luogo;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private Docente docente;
    private String ruoloDocente;
    private String categoria;

    public Attivita() {
        id = 0;
    }

    public int getId() {
        return id;
    }

    public Attivita setId(int id) {
        this.id = id;
        return this;
    }

    public String getLuogo() {
        return luogo;
    }

    public Attivita setLuogo(String luogo) {
        this.luogo = luogo;
        return this;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public Attivita setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
        return this;
    }

    public LocalTime getOraFine() {
        return oraFine;
    }

    public Attivita setOraFine(LocalTime oraFine) {
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
