package org.oop.model.entities;

import org.oop.model.Agenda;
import org.oop.model.Libretto;

/**
 * Created by MelvinMancini on 20/12/14.
 */
public class Utente {

    /* Dichiarazione degli attributi */
    private int matricola;
    private String nome;
    private String cognome;
    private String email;
    private Agenda agenda;
    private Libretto libretto;

    /* Metodo costruttore */
    public Utente(int matricola, String nome, String cognome, String email, Agenda agenda, Libretto libretto) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.agenda = agenda;
        this.libretto = libretto;
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


}