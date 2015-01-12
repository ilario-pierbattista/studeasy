package org.oop.model.entities;

import java.util.Calendar;


/**
 * Classe che descrive un'attività con ripetizione settimanale.
 */
public class AttivitaPeriodica extends Attivita {

    /**
     * Rappresenta un giorno della settimana. Assume uno dei valori possibili per DAY_OF_WEEK di java.util.Calendar
     */
    private int giorno;

    public AttivitaPeriodica() {
        super();
    }

    public int getGiorno() {
        return giorno;
    }

    public AttivitaPeriodica setGiorno(int giorno) {
        this.giorno = giorno;
        return this;
    }

    public String getNomeGiorno() {
        String nome;
        switch (giorno) {
            case Calendar.MONDAY:
                nome = "Lunedì";
                break;
            case Calendar.TUESDAY:
                nome = "Martedì";
                break;
            case Calendar.WEDNESDAY:
                nome = "Mercoledì";
                break;
            case Calendar.THURSDAY:
                nome = "Giovedì";
                break;
            case Calendar.FRIDAY:
                nome = "Venerdì";
                break;
            case Calendar.SATURDAY:
                nome = "Sabato";
                break;
            case Calendar.SUNDAY:
                nome = "Domenica";
                break;
            default:
                nome = "Giorno non riconosciuto";
                break;
        }
        return nome;
    }
}
