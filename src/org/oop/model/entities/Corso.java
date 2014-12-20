package org.oop.model.entities;

import java.util.ArrayList;


/**
 * Created by MelvinMancini on 20/12/14.
 */
public class Corso {

    /* Dichiarazione degli attributi */
    private String nome;
    private int livello;
    private int totaleCfu;
    private ArrayList<InsegnamentoOfferto> insegnamentiOfferti;

    /* Metodo Costruttore */
    public Corso(String nome, int livello, int totaleCfu, ArrayList<InsegnamentoOfferto> insegnamentiOfferti) {
        this.nome = nome;
        this.livello = livello;
        this.totaleCfu = totaleCfu;
        this.insegnamentiOfferti = insegnamentiOfferti;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public int getTotaleCfu() {
        return totaleCfu;
    }

    public void setTotaleCfu(int totaleCfu) {
        this.totaleCfu = totaleCfu;
    }

    public ArrayList<InsegnamentoOfferto> getInsegnamentiOfferti() {
        return insegnamentiOfferti;
    }

    public void setInsegnamentiOfferti(ArrayList<InsegnamentoOfferto> insegnamentiOfferti) {
        this.insegnamentiOfferti = insegnamentiOfferti;
    }


}
