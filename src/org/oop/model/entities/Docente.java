/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

public class Docente {

    private String nome;
    private String cognome;
    private String email;
    private int id;


    public Docente(String name, String surname, String mail) {
        nome = name;
        cognome = surname;
        email = mail;
    }

    public int getId() {
        return id;
    }

    public Docente setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Docente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCognome() {
        return cognome;
    }

    public Docente setCognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Docente setEmail(String email) {
        this.email = email;
        return this;
    }
}
