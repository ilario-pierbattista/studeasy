package org.oop.model.dao;


import org.oop.db.SQLParameters;
import org.oop.model.entities.Tassa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TassaDAO extends AbstractDAO<Tassa> {
    @Override
    protected SQLParameters generaSQLParams(Tassa e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("anno_accademico", e.getAnnoAccademico())
                .add("importo", e.getImporto())
                .add("scadenza", e.getScadenza())
                .add("pagata", e.isPagata());
        return parameters;
    }

    @Override
    protected Tassa generaEntita(ResultSet rs) {
        Tassa tassa = new Tassa();
        try {
            tassa.setAnnoAccademico(rs.getInt("anno_accademico"))
                    .setImporto(rs.getDouble("importo"))
                    .setPagata(rs.getBoolean("pagata"))
                    .setScadenza(rs.getDate("scadenza"));
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return null;
    }

    @Override
    public Tassa find(int id) {
        return null;
    }

    @Override
    public ArrayList<Tassa> findAll() {
        return null;
    }

    @Override
    public ArrayList<Tassa> findBy(SQLParameters params) {
        return null;
    }

    @Override
    public void persist(Tassa entity) {

    }

    @Override
    public void update(Tassa entity) {

    }

    @Override
    public void remove(Tassa entity) {

    }
}
