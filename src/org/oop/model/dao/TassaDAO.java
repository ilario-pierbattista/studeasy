package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.Tassa;
import org.oop.model.entities.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TassaDAO extends AbstractDAO<Tassa> {
    @Override
    protected SQLParameters generaSQLParams(Tassa e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("anno_accademico", e.getAnnoAccademico())
                .add("importo", e.getImporto())
                .add("scadenza", e.getScadenza())
                .add("pagata", e.isPagata());
        return parameters;
    }

    @Override
    protected Tassa generaEntita(ResultSet rs) {
        Tassa tassa = new Tassa();
        try {
            tassa.setId(rs.getInt("id"))
                    .setAnnoAccademico(rs.getInt("anno_accademico"))
                    .setImporto(rs.getDouble("importo"))
                    .setPagata(rs.getBoolean("pagata"))
                    .setScadenza(rs.getDate("scadenza"));
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return tassa;
    }

    @Override
    public Tassa find(int id) {
        Tassa tassa = null;
        SQLParameters parameters = new SQLParameters().add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM tassa WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        try {
            if (rs.next()) {
                tassa = generaEntita(rs);
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return tassa;
    }

    @Override
    public ArrayList<Tassa> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM tassa")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Tassa> findBy(SQLParameters params) {
        String sql = "SELECT * FROM tassa WHERE "
                .concat(DatabaseUtils.generateCondition(params));
        ResultSet rs = db.createSqlStatement(sql)
                .setParameters(params)
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Tassa entity) {
        SQLParameters parameters = generaSQLParams(entity);
        String sql = "INSERT INTO tassa " +
                "(anno_accademico, importo, scadenza, pagata) VALUES " +
                "(:anno_accademico, :importo, :scadenza, :pagata)";
        int id = db.createSqlStatement(sql)
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(id);
    }

    @Override
    public void update(Tassa entity) {
        SQLParameters parameters = generaSQLParams(entity);
        String sql = "UPDATE tassa SET " +
                "anno_accademico = :anno_accademico, importo = :importo, " +
                "scadenza = :scadenza, pagata = :pagata WHERE id = :id";
        db.createSqlStatement(sql)
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(Tassa entity) {
        SQLParameters parameters = new SQLParameters().add("id", entity.getId());
        db.createSqlStatement("DELETE FROM tassa WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(0);
    }

    /**
     * Imposta l'utente per la tassa
     * @param utente Oggetto utente
     */
    public void setUtente(Utente utente) {
        ArrayList<Integer> ids = new ArrayList<Integer>(3);
        SQLParameters parameters = new SQLParameters();
        String sql;
        if (!utente.getTasse().isEmpty()) {
            for (Tassa tassa : utente.getTasse()) {
                ids.add(tassa.getId());
            }
            parameters.add("id", ids);
            sql = "UPDATE tassa SET utente = :utente WHERE "
                    .concat(DatabaseUtils.generateCondition(parameters));
            parameters.add("utente", utente.getMatricola());
            db.createSqlStatement(sql)
                    .setParameters(parameters)
                    .executeUpdate();
        }
    }
}
