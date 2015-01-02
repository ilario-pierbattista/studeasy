package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.Docente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteDAO extends AbstractDAO<Docente> {

    @Override
    public Docente find(int id) {
        SQLParameters parameters = new SQLParameters();
        Docente docente = null;
        parameters.add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM docente WHERE id = :id")
                .setParameters(parameters).getResult();
        try {
            if (rs.first()) {
                docente = generaEntita(rs);
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return docente;
    }

    @Override
    public ArrayList<Docente> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM docente")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Docente> findBy(SQLParameters params) {
        String sql = "SELECT * FROM docente " +
                "WHERE ".concat(DatabaseUtils.generateCondition(params));
        ResultSet rs = db.createSqlStatement(sql).setParameters(params).getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Docente entity) {
        SQLParameters parameters = generaSQLParams(entity);
        int key = db.createSqlStatement("INSERT INTO docente (nome,cognome,email) " +
                "VALUES (:nome,:cognome,:email)")
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(key);
    }

    @Override
    public void update(Docente entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("UPDATE docente " +
                "SET nome = :nome, cognome = :cognome, email = :email " +
                "WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(Docente entity) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", entity.getId());
        db.createSqlStatement("DELETE FROM docente WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    protected Docente generaEntita(ResultSet rs) {
        Docente docente = new Docente();
        try {
            docente.setId(rs.getInt("id"))
                    .setNome(rs.getString("nome"))
                    .setCognome(rs.getString("cognome"))
                    .setEmail(rs.getString("email"));
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return docente;
    }

    @Override
    protected SQLParameters generaSQLParams(Docente e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("nome", e.getNome())
                .add("cognome", e.getCognome())
                .add("email", e.getEmail());
        return parameters;
    }
}
