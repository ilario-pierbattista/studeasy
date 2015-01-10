package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.Iscrizione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IscrizioneDAO extends AbstractDAO<Iscrizione> {
    @Override
    protected SQLParameters generaSQLParams(Iscrizione e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("anno", e.getAnno())
                .add("anno_accademico", e.getAnnoAccademico())
                .add("utente", e.getUtente().getMatricola());
        return parameters;
    }

    @Override
    protected Iscrizione generaEntita(ResultSet rs) {
        Iscrizione iscrizione = new Iscrizione();
        try {
            iscrizione
                    .setId(rs.getInt("id"))
                    .setAnno(rs.getInt("anno"))
                    .setAnnoAccademico(rs.getInt("anno_accademico"));
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return iscrizione;
    }

    @Override
    public Iscrizione find(int id) {
        Iscrizione iscrizione = null;
        SQLParameters parameters = new SQLParameters().add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM iscrizione WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        try {
            if (rs.next()) {
                iscrizione = generaEntita(rs);
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return iscrizione;
    }

    @Override
    public ArrayList<Iscrizione> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM iscrizione")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Iscrizione> findBy(SQLParameters params) {
        String sql = "SELECT * FROM iscrizione WHERE "
                .concat(DatabaseUtils.generateCondition(params));
        ResultSet rs = db.createSqlStatement(sql)
                .setParameters(params)
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Iscrizione entity) {
        SQLParameters parameters = generaSQLParams(entity);
        String sql = "INSERT INTO iscrizione " +
                "(anno, anno_accademico, utente) VALUES (:anno, :anno_accademico, :utente)";
        int id = db.createSqlStatement(sql)
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(id);
    }

    @Override
    public void update(Iscrizione entity) {
        SQLParameters parameters = generaSQLParams(entity);
        String sql = "UPDATE iscrizione SET anno = :anno, anno_accademico = :anno_accademico, " +
                "utente = :utente WHERE id = :id";
        db.createSqlStatement(sql)
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(Iscrizione entity) {
        SQLParameters parameters = new SQLParameters().add("id", entity.getId());
        db.createSqlStatement("DELETE FROM iscrizione WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }
}
