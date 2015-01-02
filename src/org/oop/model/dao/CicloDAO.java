package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.Ciclo;
import org.oop.model.entities.Insegnamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CicloDAO extends AbstractDAO<Ciclo> {
    @Override
    protected SQLParameters generaSQLParams(Ciclo e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("label", e.getLabel())
                .add("inizio", e.getInizio())
                .add("fine", e.getFine());
        return parameters;
    }

    @Override
    protected Ciclo generaEntita(ResultSet rs) {
        Ciclo ciclo = new Ciclo();
        InsegnamentoDAO insegnamentoDAO = new InsegnamentoDAO();
        try {
            ciclo.setId(rs.getInt("id"))
                    .setLabel(rs.getString("label"))
                    .setInizio(rs.getDate("inizio"))
                    .setFine(rs.getDate("fine"));
            SQLParameters parameters = new SQLParameters();
            parameters.add("ciclo", ciclo.getId());
            ciclo.setInsegnamenti(insegnamentoDAO.findBy(parameters));
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return ciclo;
    }

    @Override
    public Ciclo find(int id) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM ciclo WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        Ciclo ciclo = null;
        try {
            if(rs.next()) {
                ciclo = generaEntita(rs);
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return ciclo;
    }

    @Override
    public ArrayList<Ciclo> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM ciclo")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Ciclo> findBy(SQLParameters params) {
        String sqlString = "SELECT * FROM ciclo WHERE ".concat(DatabaseUtils.generateCondition(params));
        ResultSet rs = db.createSqlStatement(sqlString).setParameters(params).getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Ciclo entity) {
        SQLParameters parameters = generaSQLParams(entity);
        int id = db.createSqlStatement("INSERT INTO ciclo " +
                "(label, inizio, fine) " +
                "VALUES (:label, :inizio, :fine)")
                .setParameters(parameters)
                .executeUpdate();
        /* @TODO Aggiungere il collegamento con gli insegnamenti */
        entity.setId(id);
    }

    @Override
    public void update(Ciclo entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("UPDATE ciclo " +
                "SET label = :label, inizio = :inizio, fine = :fine " +
                "WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(Ciclo entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("DELETE FROM ciclo WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(0);
    }
}
