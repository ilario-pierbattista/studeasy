/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.sql.Time;
import java.util.Date;

public class Esame extends AttivitaEvento {

    //da decidere se il seguente attributo sarà una stringa o meno
    private String tipologiaProva;

    //viene usato il this nel costruttore per sopperire all'eventuale possibile confusione creata dall'elevato numero di parametri
    public Esame(String aula, Time oraInizio, Time oraFine, Docente docente, String ruoloDocente, String categoria, Date data, String tipologiaProva) {
        super(aula, oraInizio, oraFine, docente, ruoloDocente, categoria, data);
        this.tipologiaProva = tipologiaProva;
    }


    public String getTipologiaProva() {
        return tipologiaProva;
    }

    public void setTipologiaProva(String tipologiaProva) {
        this.tipologiaProva = tipologiaProva;
    }
}
