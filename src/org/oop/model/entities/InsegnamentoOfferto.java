/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

public class InsegnamentoOfferto {

    private String nome;
    private int cfu;
    private int anno;
    private int semestre;
    private boolean opzionale;
    private Docente docente;


    //i nomi dei parametri sono stati scritti in inglese per evitare di usare il this
    public InsegnamentoOfferto(String name, int credits, int year, int semester, boolean optional, Docente teacher) {
        nome = name;
        cfu = credits;
        anno = year;
        semestre = semester;
        opzionale = optional;
        docente = teacher;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public boolean isOpzionale() {
        return opzionale;
    }

    public void setOpzionale(boolean opzionale) {
        this.opzionale = opzionale;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}
