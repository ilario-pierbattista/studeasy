package org.oop.model.entities;

public class Docente {

    private int id;
    private String nome;
    private String cognome;
    private String email;


    public Docente(int id, String nome, String cognome, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }


    //il seguente è il costruttore che inizializza i dati di deafult
    public Docente() {}


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

    @Override
    public String toString() {
        return "Docente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
