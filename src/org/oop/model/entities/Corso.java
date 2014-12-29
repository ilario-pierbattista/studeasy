package org.oop.model.entities;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by MelvinMancini on 20/12/14.
 */
public class Corso {

    /* Dichiarazione degli attributi */
    private int id;
    private String nome;
    private int livello;
    private int totaleCfu;
    private ArrayList<InsegnamentoOfferto> insegnamentiOfferti;


    public Corso(int id, String nome, int livello, int totaleCfu, ArrayList<InsegnamentoOfferto> insegnamentiOfferti) {
        this.id = id;
        this.nome = nome;
        this.livello = livello;
        this.totaleCfu = totaleCfu;
        this.insegnamentiOfferti = insegnamentiOfferti;
    }


    //il seguente è il costruttore che inizializza i dati di deafult
    public Corso () {
        ArrayList<InsegnamentoOfferto> insegnamentiOfferti = new ArrayList<InsegnamentoOfferto>(1);
    }



    /**
     * Il metodo getInsegnamentiObbligatori restituisce un ArrayList i cui elementi sono oggetti della Classe
     * InsegnamentoOfferto con il valore dell'attributo opzionale a false, quindi sono insegnamenti obbligatori.
     *
     * @return
     */
    public ArrayList<InsegnamentoOfferto> getInsegnamentiObbligatori() {
        ArrayList<InsegnamentoOfferto> insegnamentiObbligatori = new ArrayList<InsegnamentoOfferto>(10);
        for (InsegnamentoOfferto insegnamento : insegnamentiOfferti) {
            if (insegnamento.isOpzionale() == false) {
                insegnamentiObbligatori.add(insegnamento);
            }
        }
        return insegnamentiObbligatori;
    }

    /**
     * Il metodo getInsegnamentiObbligatori restituisce un ArrayList i cui elementi sono oggetti della Classe
     * InsegnamentoOfferto con il valore dell'attributo opzionale a true, quindi sono insegnamenti opzionali.
     *
     * @return
     */
    public ArrayList<InsegnamentoOfferto> getInsegnamentiOpzionali() {
        ArrayList<InsegnamentoOfferto> insegnamentiOpzionali = new ArrayList<InsegnamentoOfferto>(10);
        for (InsegnamentoOfferto insegnamento : insegnamentiOfferti) {
            if (insegnamento.isOpzionale() == true) {
                insegnamentiOpzionali.add(insegnamento);
            }
        }
        return insegnamentiOpzionali;
    }

    public int getId() {
        return id;
    }

    public Corso setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Corso setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getLivello() {
        return livello;
    }

    public Corso setLivello(int livello) {
        this.livello = livello;
        return this;
    }

    public int getTotaleCfu() {
        return totaleCfu;
    }

    public Corso setTotaleCfu(int totaleCfu) {
        this.totaleCfu = totaleCfu;
        return this;
    }

    public ArrayList<InsegnamentoOfferto> getInsegnamentiOfferti() {
        return insegnamentiOfferti;
    }

    public Corso setInsegnamentiOfferti(ArrayList<InsegnamentoOfferto> insegnamentiOfferti) {
        this.insegnamentiOfferti = insegnamentiOfferti;
        return this;
    }

    public Corso addInsegnamentoOfferto(InsegnamentoOfferto insegnamentoOfferto) {
        insegnamentiOfferti.add(insegnamentoOfferto);
        return this;
    }

    public boolean hasInsegnamentoOfferto(InsegnamentoOfferto insegnamentoOfferto) {
        boolean found = false;
        for (int i = 0; i < insegnamentiOfferti.size() && !found; i++) {
            found = insegnamentiOfferti.get(i).equals(insegnamentoOfferto);
        }
        return found;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corso)) return false;

        Corso corso = (Corso) o;

        if (livello != corso.livello) return false;
        if (totaleCfu != corso.totaleCfu) return false;
        if (nome != null ? !nome.equals(corso.nome) : corso.nome != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", livello=" + livello +
                ", totaleCfu=" + totaleCfu +
                ", insegnamentiOfferti=" + insegnamentiOfferti +
                '}';
    }
}
