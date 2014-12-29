package org.oop.model.dao;


import org.oop.db.DatabaseManager;
import org.oop.model.entities.InsegnamentoOfferto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDAO<T> implements DAOInterface<T> {
    protected DatabaseManager db;

    public AbstractDAO() {
        db = new DatabaseManager();
    }

    protected ArrayList<T> generaArrayEntita(ResultSet rs) {
        ArrayList<T> entita = new ArrayList<T>(100);
        try {
            while (rs.next()) {
                entita.add(generaEntita(rs));
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            db.closeConnection();
        }
        return entita;
    }

    protected abstract T generaEntita(ResultSet rs);

    public void flush() {
        db.commit();
    }

}
