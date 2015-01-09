package org.oop.model.entities;

import java.time.LocalTime;
import java.util.Date;

public class Esame extends AttivitaEvento {

    public static final String TIPOLOGIA_ORALE = "orale";
    public static final String TIPOLOGIA_SCRITTO = "scritto";
    public static final String TIPOLOGIA_LABORATORIO = "laboratorio";

    private String tipologiaProva;

    //viene usato il this nel costruttore per sopperire all'eventuale possibile confusione creata dall'elevato numero di parametri
    public Esame(String aula, LocalTime oraInizio, LocalTime oraFine, Docente docente, String ruoloDocente, String categoria, Date data, String tipologiaProva) {
        super(aula, oraInizio, oraFine, docente, ruoloDocente, categoria, data);
        this.tipologiaProva = tipologiaProva;
    }

    //il seguente è il costruttore che inizializza i dati di deafult
    public Esame() {
    }

    public String getTipologiaProva() {
        return tipologiaProva;
    }

    public Esame setTipologiaProva(String tipologiaProva) {
        this.tipologiaProva = tipologiaProva;
        return this;
    }
}
