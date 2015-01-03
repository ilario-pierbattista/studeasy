package org.oop.model.dao;


import org.oop.db.SQLParameters;
import org.oop.model.entities.Attivita;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AttivitaDAO extends AbstractDAO<Attivita> {
    @Override
    protected SQLParameters generaSQLParams(Attivita e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("luogo", e.getLuogo())
                .add("ora_inizio", e.getOraInizio())
                .add("ora_fine", e.getOraFine())
                .add("docente", e.getDocente().getId());
        return null;
    }

    @Override
    protected Attivita generaEntita(ResultSet rs) {
        return null;
    }

    @Override
    public Attivita find(int id) {
        return null;
    }

    @Override
    public ArrayList<Attivita> findAll() {
        return null;
    }

    @Override
    public ArrayList<Attivita> findBy(SQLParameters params) {
        return null;
    }

    @Override
    public void persist(Attivita entity) {

    }

    @Override
    public void update(Attivita entity) {

    }

    @Override
    public void remove(Attivita entity) {

    }
}
