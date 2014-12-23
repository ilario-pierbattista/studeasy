package org.oop.model.dao;


import org.oop.db.SQLParameters;
import org.oop.model.entities.Corso;
import org.oop.model.entities.InsegnamentoOfferto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            db.closeConnection();
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
        return null;
    }

    @Override
    public void persist(Corso entity) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("nome", entity.getNome())
                .add("livello", entity.getLivello())
                .add("totale_cfu", entity.getTotaleCfu());
        int id = db.createSqlStatement("INSERT INTO corso (nome, livello, totale_cfu) VALUES (:nome, :livello, :totale_cfu)")
                .setParameters(parameters).executeUpdate();
        entity.setId(id);
    }

    @Override
    public void update(Corso entity) {

    }

    @Override
    public void remove(Corso entity) {

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
}
