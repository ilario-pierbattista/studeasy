package org.oop.model.dao;


import org.oop.db.DatabaseManager;
import org.oop.db.SQLParameters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Classe che definisce i metodi che una classe DAO deve implementare.
 * <p/>
 * DAO sta per Data Access Object. Implementa tutta la logica per la lettura e la scrittura sul database per una classe
 * entità.
 *
 * @param <T> Classe entità per cui si implementa il DAO.
 */
public abstract class AbstractDAO<T> {
    protected DatabaseManager db;

    public AbstractDAO() {
        db = DatabaseManager.getInstance();
    }

    /**
     * Cerca un'entit&agrave; attraverso la sua chiave primaria.
     *
     * @param id Chiave primaria
     * @return Istanza dell'entit&agrave; se presente oppure null
     */
    public abstract T find(int id);

    /**
     * Cerca tutte le entit&agrave; presenti nel database.
     *
     * @return ArrayList di entit&agrave;
     */
    public abstract ArrayList<T> findAll();

    /**
     * Cerca le entità a partire dai parametri.
     *
     * @param params Oggetto SQLParameters
     * @return ArrayList di oggetti entità
     * @see org.oop.db.SQLParameters
     */
    public abstract ArrayList<T> findBy(SQLParameters params);

    /**
     * Inserisce i dati di un'entità nel database. Imposta, al momento della creazione, l'attributo identificativo
     * univoco dell'entità.
     *
     * @param entity Oggetto entità da inserire
     */
    public abstract void persist(T entity);

    /**
     * Aggiorna i dati di un'entità nel database
     *
     * @param entity Oggetto entità da aggiornare
     */
    public abstract void update(T entity);

    /**
     * Rimuove un'entità dal database
     *
     * @param entity Oggetto entità da rimuovere
     */
    public abstract void remove(T entity);

    /**
     * Genera un ArrayList di entità a partire dal un oggetto ResultSet di un query
     *
     * @param rs Risultato di una query
     * @return Array di entità
     */
    protected ArrayList<T> generaArrayEntita(ResultSet rs) {
        ArrayList<T> entita = new ArrayList<T>(100);
        try {
            while (rs.next()) {
                entita.add(generaEntita(rs));
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return entita;
    }

    /**
     * Genera, da un oggetto entità, un oggetto SQLParameters i cui parametri sono gli attributi dell'entità con
     * corrispondenza nel database.
     *
     * @param e Oggetto entità
     * @return Oggetto SQLParameters generato
     */
    protected abstract SQLParameters generaSQLParams(T e);

    /**
     * Genera, a partire dal ResultSet di una query, l'oggetto entità che
     *
     * @param rs Oggetto ResultSet
     * @return Oggetto entità.
     */
    protected abstract T generaEntita(ResultSet rs);

    /**
     * Rende effettive le azioni di insert, update e delete effettuate e chiude la connessione
     */
    public void flush() {
        db.commit();
    }

    /**
     * Annulla le azioni effettuate sul database tornando allo stato dell'ultima commit
     */
    public void undo() {
        db.rollback();
    }
}
