package org.oop.model.entities;

import org.oop.model.Agenda;
import org.oop.model.Libretto;

import java.util.ArrayList;


/**
 * Classe che descrive un utente del software
 */
public class Utente {

    private int matricola;
    private String nome;
    private String cognome;
    private String email;
    private Agenda agenda;
    private Libretto libretto;
    private ArrayList<Tassa> tasse;
    private ArrayList<Iscrizione> iscrizioni;

    public Utente() {
        matricola = 0;
        libretto = new Libretto();
        agenda = new Agenda();
        tasse = new ArrayList<Tassa>(3);
        iscrizioni = new ArrayList<Iscrizione>(3);
    }

    public int getMatricola() {
        return matricola;
    }

    public Utente setMatricola(int matricola) {
        this.matricola = matricola;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Utente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCognome() {
        return cognome;
    }

    public Utente setCognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Utente setEmail(String email) {
        this.email = email;
        return this;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public Utente setAgenda(Agenda agenda) {
        this.agenda = agenda;
        return this;
    }

    public Libretto getLibretto() {
        return libretto;
    }

    public Utente setLibretto(Libretto libretto) {
        this.libretto = libretto;
        return this;
    }

    public ArrayList<Tassa> getTasse() {
        return tasse;
    }

    public Utente setTasse(ArrayList<Tassa> tasse) {
        this.tasse = tasse;
        return this;
    }

    public Utente addTassa(Tassa tassa) {
        tasse.add(tassa);
        return this;
    }

    public Tassa findTassa(int id) {
        Tassa tassa = null;
        boolean found = false;
        for (int i = 0; i < tasse.size() && !found; i++) {
            if (id == tasse.get(i).getId()) {
                found = true;
                tassa = tasse.get(i);
            }
        }
        return tassa;
    }

    public Utente removeTassa(int id) {
        boolean found = false;
        for (int i = 0; i < tasse.size() && !found; i++) {
            if (tasse.get(i).getId() == id) {
                found = true;
                tasse.remove(i);
            }
        }
        return this;
    }

    public ArrayList<Iscrizione> getIscrizioni() {
        return iscrizioni;
    }

    public Utente setIscrizioni(ArrayList<Iscrizione> iscrizioni) {
        this.iscrizioni = iscrizioni;
        for (Iscrizione iscrizione : iscrizioni) {
            iscrizione.setUtente(this);
        }
        return this;
    }

    public Utente addIscrizione(Iscrizione iscrizione) {
        iscrizioni.add(iscrizione);
        return this;
    }

    public Utente removeIscrizione(int id) {
        boolean found = false;
        for (int i = 0; i < iscrizioni.size() && !found; i++) {
            if (iscrizioni.get(i).getId() == id) {
                iscrizioni.remove(i);
                found = true;
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "matricola=" + matricola +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", agenda=" + agenda +
                ", libretto=" + libretto +
                '}';
    }
}
