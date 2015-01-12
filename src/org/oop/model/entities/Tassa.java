package org.oop.model.entities;


import java.util.Date;


/**
 * Rappresenta i dati riguardanti una tassa d'iscrizione
 */
public class Tassa {
    private int id;
    private int annoAccademico;
    private double importo;
    private Date scadenza;
    private boolean pagata;

    public Tassa() {
        id = 0;
    }

    public int getId() {
        return id;
    }

    public Tassa setId(int id) {
        this.id = id;
        return this;
    }

    public int getAnnoAccademico() {
        return annoAccademico;
    }

    public Tassa setAnnoAccademico(int annoAccademico) {
        this.annoAccademico = annoAccademico;
        return this;
    }

    public double getImporto() {
        return importo;
    }

    public Tassa setImporto(double importo) {
        this.importo = importo;
        return this;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public Tassa setScadenza(Date scadenza) {
        this.scadenza = scadenza;
        return this;
    }

    public boolean isPagata() {
        return pagata;
    }

    public Tassa setPagata(boolean pagata) {
        this.pagata = pagata;
        return this;
    }

    /**
     * Restituisce una stringa che traduce l'attributo pagata
     *
     * @return "Pagata" o "Non pagata"
     */
    public String isPagataToString() {
        String stato;
        if (pagata) {
            stato = "Pagata";
        } else {
            stato = "Non pagata";
        }

        return stato;
    }
}
