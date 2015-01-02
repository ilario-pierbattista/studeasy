package org.oop.model.entities;

public class InsegnamentoOfferto {

    private int id;
    private String nome;
    private int cfu;
    private int anno;
    private int semestre;
    private boolean opzionale;
    private Docente docente;

    //il seguente è il costruttore che inizializza i dati di deafult
    public InsegnamentoOfferto() {
    }

    //i nomi dei parametri sono stati scritti in inglese per evitare di usare il this
    public InsegnamentoOfferto(String name, int credits, int year, int semester, boolean optional, Docente teacher) {
        nome = name;
        cfu = credits;
        anno = year;
        semestre = semester;
        opzionale = optional;
        docente = teacher;
    }

    public int getId() {
        return id;
    }

    public InsegnamentoOfferto setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public InsegnamentoOfferto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getCfu() {
        return cfu;
    }

    public InsegnamentoOfferto setCfu(int cfu) {
        this.cfu = cfu;
        return this;
    }

    public int getAnno() {
        return anno;
    }

    public InsegnamentoOfferto setAnno(int anno) {
        this.anno = anno;
        return this;
    }

    public int getSemestre() {
        return semestre;
    }

    public InsegnamentoOfferto setSemestre(int semestre) {
        this.semestre = semestre;
        return this;
    }

    public boolean isOpzionale() {
        return opzionale;
    }

    public InsegnamentoOfferto setOpzionale(boolean opzionale) {
        this.opzionale = opzionale;
        return this;
    }

    public Docente getDocente() {
        return docente;
    }

    public InsegnamentoOfferto setDocente(Docente docente) {
        this.docente = docente;
        return this;
    }

    @Override
    public String toString() {
        return "InsegnamentoOfferto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cfu=" + cfu +
                ", anno=" + anno +
                ", semestre=" + semestre +
                ", opzionale=" + opzionale +
                ", docente=" + docente +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsegnamentoOfferto)) return false;

        InsegnamentoOfferto that = (InsegnamentoOfferto) o;

        if (anno != that.anno) return false;
        if (cfu != that.cfu) return false;
        if (opzionale != that.opzionale) return false;
        if (semestre != that.semestre) return false;
        if (!nome.equals(that.nome)) return false;

        return true;
    }
}
