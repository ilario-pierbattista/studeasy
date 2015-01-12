package org.oop.model.entities;


/**
 * Classe che descrive un'esame
 */
public class Esame extends AttivitaEvento {

    /**
     * Valori assumibili dall'atributo tipologiaProva
     */
    public static final String TIPOLOGIA_ORALE = "orale";
    public static final String TIPOLOGIA_SCRITTO = "scritto";
    public static final String TIPOLOGIA_LABORATORIO = "laboratorio";

    private String tipologiaProva;

    public Esame() {
        super();
    }

    public String getTipologiaProva() {
        return tipologiaProva;
    }

    public Esame setTipologiaProva(String tipologiaProva) {
        this.tipologiaProva = tipologiaProva;
        return this;
    }
}
