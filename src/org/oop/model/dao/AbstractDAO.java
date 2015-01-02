package org.oop.model.dao;


import org.oop.db.DatabaseManager;
import org.oop.db.SQLParameters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDAO<T> implements DAOInterface<T> {
    protected DatabaseManager db;

    public AbstractDAO() {
        db = DatabaseManager.getInstance();
    }

    /**
     * Genera un ArrayList di entità a partire dal un oggetto
     * ResultSet di un query
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

    protected abstract SQLParameters generaSQLParams(T e);

    protected abstract T generaEntita(ResultSet rs);

    /**
     * Rende effettive le azioni di insert, update e delete
     * effettuate e chiude la connessione
     */
    public void flush() {
        db.commit();
    }

    public void undo() {
        db.rollback();
    }
}
