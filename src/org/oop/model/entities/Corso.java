package org.oop.model.entities;

import java.util.ArrayList;


/**
 * Classe che rappresenta un corso di laurea
 */
public class Corso {

    /**
     * Valori assumibili dall'attributo livello
     */
    public static final int TRIENNALE = 1;
    public static final int MAGISTRALE = 2;
    public static final int CICLO_UNICO = 0;

    private int id;
    private String nome;
    private int livello;
    private int totaleCfu;
    private ArrayList<InsegnamentoOfferto> insegnamentiOfferti;


    public Corso() {
        id = 0;
        insegnamentiOfferti = new ArrayList<InsegnamentoOfferto>(20);
    }

    /**
     * Restituisce un ArrayList i cui elementi sono oggetti della classe InsegnamentoOfferto con il valore
     * dell'attributo opzionale a false, quindi sono insegnamenti obbligatori.
     *
     * @return Arraylist di oggetti InsegnamentoOfferto obbligatori
     */
    public ArrayList<InsegnamentoOfferto> getInsegnamentiObbligatori() {
        ArrayList<InsegnamentoOfferto> insegnamentiObbligatori = new ArrayList<InsegnamentoOfferto>(10);
        for (InsegnamentoOfferto insegnamento : insegnamentiOfferti) {
            if (!insegnamento.isOpzionale()) {
                insegnamentiObbligatori.add(insegnamento);
            }
        }
        return insegnamentiObbligatori;
    }

    /**
     * Restituisce un ArrayList i cui elementi sono oggetti della classe InsegnamentoOfferto con il valore
     * dell'attributo opzionale a true, quindi sono insegnamenti opzionali.
     *
     * @return ArrayList di oggetti InsegnamentoOfferto opzionali
     */
    public ArrayList<InsegnamentoOfferto> getInsegnamentiOpzionali() {
        ArrayList<InsegnamentoOfferto> insegnamentiOpzionali = new ArrayList<InsegnamentoOfferto>(10);
        for (InsegnamentoOfferto insegnamento : insegnamentiOfferti) {
            if (insegnamento.isOpzionale()) {
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

    /**
     * Ritorna il nome del livello del corso di laurea in base alla convenzione utilizzata
     *
     * @return "Triennale", "Magistrale" o "Ciclo Unico"
     */
    public String getNomeLivello() {
        String nome;
        if (livello == TRIENNALE) {
            nome = "Triennale";
        } else if (livello == MAGISTRALE) {
            nome = "Magistrale";
        } else {
            nome = "Ciclo Unico";
        }
        return nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corso)) return false;

        Corso corso = (Corso) o;

        if (livello != corso.livello) return false;
        if (totaleCfu != corso.totaleCfu) return false;
        //l'operatore "?", se la prima condizione è vera, esegue la prima istruzione, altrimenti la seconda
        if (nome != null ? !nome.equals(corso.nome) : corso.nome != null) return false;

        return true;
    }

    public String toString(boolean debugMode) {
        String str;
        if (debugMode) {
            str = "Corso{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", livello=" + livello +
                    ", totaleCfu=" + totaleCfu +
                    ", insegnamentiOfferti=" + insegnamentiOfferti +
                    '}';
        } else {
            str = nome;
        }
        return str;
    }

    @Override
    public String toString() {
        return toString(false);
    }
}
