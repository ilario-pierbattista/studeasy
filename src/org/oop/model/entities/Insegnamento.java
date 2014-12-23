/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.util.Date;

public class Insegnamento extends InsegnamentoOfferto {
    private int voto;
    private boolean lode;
    private Date data;

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
