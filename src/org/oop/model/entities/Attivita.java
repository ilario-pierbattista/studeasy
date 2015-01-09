package org.oop.model.entities;

import java.time.LocalTime;

public class Attivita {
    /**
     * Valori assumibili dall'attributo categoria
     */
    public static final String CATEGORIA_LEZIONE = "lezione";
    public static final String CATEGORIA_LABORATORIO = "laboratorio";
    public static final String CATEGORIA_PROGETTO = "progetto";
    public static final String CATEGORIA_ESAME = "esame";
    public static final String CATEGORIA_SEMINARIO = "seminario";

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
    //da decidere se i seguenti attributi saranno o meno stringhe
    private String ruoloDocente;
    private String categoria;


    //per il costruttore è stato usato il this per evitare confusione nella lettura del codice dato l'elevato numero di parametri
    public Attivita(String aula, LocalTime oraInizio, LocalTime oraFine, Docente docente, String ruoloDocente, String categoria) {
        this.luogo = aula;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.docente = docente;
        this.ruoloDocente = ruoloDocente;
        this.categoria = categoria;
    }


    //il seguente è il costruttore che inizializza i dati di deafult
    public Attivita() {
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
