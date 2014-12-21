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

    public void setId(int id) {
        this.id = id;
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
}
