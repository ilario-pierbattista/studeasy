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

    public String getNomeCognome() {
        return nome.concat(" ").concat(cognome);
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
        return this.cognome + " " + this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Docente)) return false;

        Docente docente = (Docente) o;

        if (cognome != null ? !cognome.equals(docente.cognome) : docente.cognome != null) return false;
        if (email != null ? !email.equals(docente.email) : docente.email != null) return false;
        if (nome != null ? !nome.equals(docente.nome) : docente.nome != null) return false;

        return true;
    }
}
