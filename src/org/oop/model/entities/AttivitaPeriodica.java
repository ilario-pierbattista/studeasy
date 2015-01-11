package org.oop.model.entities;

import java.time.LocalTime;
import java.util.Calendar;

public class AttivitaPeriodica extends Attivita {

    /**
     * Rappresenta un giorno della settimana. Assume uno dei valori possibili per DAY_OF_WEEK di java.util.Calendar
     */
    private int giorno;

    //il costruttore della classe figlia "amplia" quello della classe padre assegnando i valori non inizializzati
    public AttivitaPeriodica(String aula, LocalTime oraInizio, LocalTime oraFine, Docente docente, String ruoloDocente, String categoria, int giorno) {
        super(aula, oraInizio, oraFine, docente, ruoloDocente, categoria);
        this.giorno = giorno;
    }

    //il seguente è il costruttore che inizializza i dati di deafult
    public AttivitaPeriodica() {
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
