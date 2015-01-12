package org.oop.model.entities;


/**
 * Rappresenta i dati riguardanti un'iscrizione
 */
public class Iscrizione {
    private int id;
    private int anno;
    private int annoAccademico;
    private Utente utente;

    public Iscrizione() {
        id = 0;
    }

    public int getId() {
        return id;
    }

    public Iscrizione setId(int id) {
        this.id = id;
        return this;
    }

    public int getAnno() {
        return anno;
    }

    public Iscrizione setAnno(int anno) {
        this.anno = anno;
        return this;
    }

    public int getAnnoAccademico() {
        return annoAccademico;
    }

    public Iscrizione setAnnoAccademico(int annoAccademico) {
        this.annoAccademico = annoAccademico;
        return this;
    }

    public Utente getUtente() {
        return utente;
    }

    public Iscrizione setUtente(Utente utente) {
        this.utente = utente;
        return this;
    }
}
