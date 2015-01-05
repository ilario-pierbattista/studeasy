package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.Attivita;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.InsegnamentoOfferto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsegnamentoDAO extends AbstractDAO<Insegnamento> {
    @Override
    protected SQLParameters generaSQLParams(Insegnamento e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("voto", e.getVoto())
                .add("lode", e.isLode())
                .add("data", e.getData())
                .add("insegnamento", e.getInsegnamentoOffertoId());
        return parameters;
    }

    @Override
    protected Insegnamento generaEntita(ResultSet rs) {
        Insegnamento insegnamento = null;
        InsegnamentoOffertoDAO insegnamentoOffertoDAO = new InsegnamentoOffertoDAO();
        try {
            InsegnamentoOfferto insOff = insegnamentoOffertoDAO.find(rs.getInt("insegnamento"));
            insegnamento = new Insegnamento(insOff);
            insegnamento.setId(rs.getInt("id"))
                    .setVoto(rs.getInt("voto"))
                    .setLode(rs.getBoolean("lode"))
                    .setData(rs.getDate("data"));
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return insegnamento;
    }

    @Override
    public Insegnamento find(int id) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM insegnamento_utente WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        Insegnamento ins = null;
        try {
            if(rs.next()) {
                ins = generaEntita(rs);
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return ins;
    }

    @Override
    public ArrayList<Insegnamento> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM insegnamento_utente")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Insegnamento> findBy(SQLParameters params) {
        String sql = "SELECT * FROM insegnamento_utente WHERE ".concat(DatabaseUtils.generateCondition(params));
        ResultSet rs = db.createSqlStatement(sql).setParameters(params).getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Insegnamento entity) {
        SQLParameters parameters = generaSQLParams(entity);
        int id = db.createSqlStatement("INSERT INTO insegnamento_utente " +
                "(insegnamento, voto, lode, data) " +
                "VALUES (:insegnamento, :voto, :lode, :data)")
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(id);
    }

    @Override
    public void update(Insegnamento entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("UPDATE insegnamento_utente " +
                "SET insegnamento = :insegnamento, voto = :voto, lode = :lode, data = :data " +
                "WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(Insegnamento entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("DELETE FROM insegnamento_utente WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(0);
    }
}
