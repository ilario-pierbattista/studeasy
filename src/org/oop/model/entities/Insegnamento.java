/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.util.Date;

public class Insegnamento extends InsegnamentoOfferto {

    private int voto;
    private boolean lode;
    private Date data;
    private Docente docente;
    //da decidere se i due seguenti attributi saranno una stringa o meno
    private String ruoloDocente;
    private String categoria;



    //è stato usato il this perchè il numero di parametri da passare al costruttore è elevato
    public Insegnamento(String name, int credits, int year, int semester, boolean optional, Docente teacher, int voto, boolean lode, Date data, String ruoloDocente, String categoria) {
        super(name, credits, year, semester, optional, teacher);
        this.voto = voto;
        this.lode = lode;
        this.data = data;
        this.ruoloDocente = ruoloDocente;
        this.categoria = categoria;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public boolean isLode() {
        return lode;
    }

    public void setLode(boolean lode) {
        this.lode = lode;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public Docente getDocente() {
        return docente;
    }

    @Override
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getRuoloDocente() {
        return ruoloDocente;
    }

    public void setRuoloDocente(String ruoloDocente) {
        this.ruoloDocente = ruoloDocente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
