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

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Libretto getLibretto() {
        return libretto;
    }

    public void setLibretto(Libretto libretto) {
        this.libretto = libretto;
    }


}
