/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Insegnamento extends InsegnamentoOfferto {
    private int voto;
    private boolean lode;
    private Date data;
    private ArrayList<Attivita> attivita;

    /* @TODO Eliminare se non serve */
    public Insegnamento(String name, int credits, int year, int semester, boolean optional, Docente teacher, int voto, boolean lode, Date data) {
        super(name, credits, year, semester, optional, teacher);
        this.voto = voto;
        this.lode = lode;
        this.data = data;
    }

    //il seguente è il costruttore che inizializza i dati di deafult
    public Insegnamento() {
        attivita = new ArrayList<Attivita>(3);
    }

    /* @TODO pensare di includere l'insegnamentoOfferto come attributo piuttosto che
    come classe genitore
     */
    public Insegnamento(InsegnamentoOfferto insegnamentoOfferto) {
        super();
        super.id = insegnamentoOfferto.id;
        nome = insegnamentoOfferto.nome;
        anno = insegnamentoOfferto.anno;
        semestre = insegnamentoOfferto.semestre;
        cfu = insegnamentoOfferto.cfu;
        opzionale = insegnamentoOfferto.opzionale;
        docente = insegnamentoOfferto.docente;
    }

    public int getId() {
        return id;
    }

    public Insegnamento setId(int id) {
        this.id = id;
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

    public int getInsegnamentoOffertoId() {
        return super.id;
    }

    public Insegnamento setInsegnamentoOffertoId(int id) {
        super.id = id;
        return this;
    }
}
