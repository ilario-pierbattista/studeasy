package org.oop.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Insegnamento {
    private int id;
    private InsegnamentoOfferto insegnamentoOfferto;
    private int voto;
    private boolean lode;
    private Date data;
    private ArrayList<Attivita> attivita;

    //il seguente è il costruttore che inizializza i dati di deafult
    public Insegnamento() {
        attivita = new ArrayList<Attivita>(3);
    }

    public Insegnamento(InsegnamentoOfferto insegnamentoOfferto) {
        this();
        this.insegnamentoOfferto = insegnamentoOfferto;
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

    @Override
    public String toString() {
        return "Insegnamento{" +
                "id=" + id +
                ", insegnamentoOfferto=" + insegnamentoOfferto +
                ", voto=" + voto +
                ", lode=" + lode +
                ", data=" + data +
                ", attivita=" + attivita +
                '}';
    }
}