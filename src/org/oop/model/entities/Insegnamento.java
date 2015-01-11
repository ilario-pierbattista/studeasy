package org.oop.model.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Insegnamento {
    private int id;
    private InsegnamentoOfferto insegnamentoOfferto;
    private int voto;
    private boolean lode;
    private Date data;
    private ArrayList<Attivita> attivita;

    public Insegnamento() {
        attivita = new ArrayList<Attivita>(3);
    }

    public Insegnamento(InsegnamentoOfferto insegnamentoOfferto) {
        this();
        this.insegnamentoOfferto = insegnamentoOfferto;
    }

    public boolean esameSostenuto() {
        return voto != 0;
    }

    /**
     * Ritorna l'anno accademico durante il quale si è sostenuto l'esame. L'anno accademico si presume che cominci da
     * Ottobre, quando la sessione estiva di recupero si è conclusa.
     *
     * @return
     */
    public int getAnnoAccademico() {
        int annoAccademico = 0;
        if (data != null) {
            // Le API di java 8 riguardo Date sono cambiare sensibilmente
            // ci potrebbero essere dei bug
            Instant instant = Instant.ofEpochMilli(data.getTime());
            LocalDateTime localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            if (localDate.getMonthValue() >= 10) {
                annoAccademico = localDate.getYear();
            } else {
                annoAccademico = localDate.getYear() - 1;
            }
        }
        return annoAccademico;
    }

    public int getId() {
        return id;
    }

    public Insegnamento setId(int id) {
        this.id = id;
        return this;
    }

    public InsegnamentoOfferto getInsegnamentoOfferto() {
        return insegnamentoOfferto;
    }

    public Insegnamento setInsegnamentoOfferto(InsegnamentoOfferto insegnamentoOfferto) {
        this.insegnamentoOfferto = insegnamentoOfferto;
        return this;
    }

    public int getVoto() {
        return voto;
    }

    public Insegnamento setVoto(int voto) {
        this.voto = voto;
        return this;
    }

    public boolean isLode() {
        return lode;
    }

    public Insegnamento setLode(boolean lode) {
        this.lode = lode;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Insegnamento setData(Date data) {
        this.data = data;
        return this;
    }

    public ArrayList<Attivita> getAttivita() {
        return attivita;
    }

    public Insegnamento setAttivita(ArrayList<Attivita> attivita) {
        this.attivita = attivita;
        return this;
    }

    public Insegnamento addAttivita(Attivita nuova) {
        attivita.add(nuova);
        return this;
    }

    public String toString() {
        return insegnamentoOfferto.toString();
    }
}