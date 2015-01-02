package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.general.Utils;
import org.oop.model.entities.Corso;
import org.oop.model.entities.InsegnamentoOfferto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorsoDAO extends AbstractDAO<Corso> {
    @Override
    public Corso find(int id) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM corso WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        Corso corso = null;
        try {
            if (rs.first()) {
                corso = generaEntita(rs);
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return corso;
    }

    @Override
    public ArrayList<Corso> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM corso").getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Corso> findBy(SQLParameters params) {
        String sql = "SELECT * FROM corso WHERE ".concat(DatabaseUtils.generateCondition(params));
        ResultSet rs = db.createSqlStatement(sql).setParameters(params).getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Corso entity) {
        SQLParameters parameters = generaSQLParams(entity);
        int id = db.createSqlStatement("INSERT INTO corso (nome, livello, totale_cfu) VALUES (:nome, :livello, :totale_cfu)")
                .setParameters(parameters).executeUpdate();
        entity.setId(id);

        SQLParameters idInsegnamenti = new SQLParameters(), sqlParameters = new SQLParameters();
        ArrayList<Integer> ids = new ArrayList<Integer>(entity.getInsegnamentiOfferti().size());
        for (InsegnamentoOfferto ins : entity.getInsegnamentiOfferti()) {
            ids.add(ins.getId());
        }
        idInsegnamenti.add("id", ids);
        db.createSqlStatement("UPDATE insegnamento SET corso = :id_corso WHERE ".concat(DatabaseUtils.generateCondition(idInsegnamenti)));
        sqlParameters.add("id_corso", id)
                .merge(idInsegnamenti);
        db.setParameters(sqlParameters)
                .executeUpdate();
    }

    @Override
    public void update(Corso entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("UPDATE corso " +
                "SET nome = :nome, livello = :livello, totale_cfu = :totale_cfu " +
                "WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(Corso entity) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", entity.getId());
        db.createSqlStatement("DELETE FROM corso WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    protected Corso generaEntita(ResultSet rs) {
        Corso corso = new Corso();
        try {
            corso.setId(rs.getInt("id"))
                    .setNome(rs.getString("nome"))
                    .setTotaleCfu(rs.getInt("totale_cfu"))
                    .setLivello(rs.getInt("livello"));
            InsegnamentoOffertoDAO insDAO = new InsegnamentoOffertoDAO();
            SQLParameters parameters = new SQLParameters();
            parameters.add("corso", corso.getId());
            ArrayList<InsegnamentoOfferto> insegnamenti = insDAO.findBy(parameters);
            corso.setInsegnamentiOfferti(insegnamenti);
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return corso;
    }

    @Override
    protected SQLParameters generaSQLParams(Corso e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("nome", e.getNome())
                .add("livello", e.getLivello())
                .add("totale_cfu", e.getTotaleCfu());
        return parameters;
    }
}
