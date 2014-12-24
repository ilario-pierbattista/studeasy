/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.util.Date;

public class Insegnamento extends InsegnamentoOfferto {
    private int voto;
    private boolean lode;
    private Date data;

    public Insegnamento(String name, int credits, int year, int semester, boolean optional, Docente teacher, int voto, boolean lode, Date data) {
        super(name, credits, year, semester, optional, teacher);
        this.voto = voto;
        this.lode = lode;
        this.data = data;
    }

    //il seguente è il costruttore che inizializza i dati di deafult
    public Insegnamento() {}

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
}
