package org.oop.model.dao;

import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.general.Utils;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UtenteDAO extends AbstractDAO<Utente> {
    @Override
    protected Utente generaEntita(ResultSet rs) {
        Utente utente = new Utente();
        try {
            utente.setMatricola(rs.getInt("matricola"))
                    .setNome(rs.getString("nome"))
                    .setCognome(rs.getString("cognome"))
                    .setEmail(rs.getString("email"));
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return utente;
    }

    @Override
    public Utente find(int id) {
        Utente utente = null;
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM utente WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        try {
            if(rs.next()) {
                utente = generaEntita(rs);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return utente;
    }

    @Override
    public ArrayList<Utente> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM utente")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Utente> findBy(SQLParameters params) {
        ResultSet rs = db.createSqlStatement("SELECT * FROM utente " +
                " WHERE ".concat(DatabaseUtils.generateCondition(params)))
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Utente entity) {

    }

    @Override
    public void update(Utente entity) {

    }

    @Override
    public void remove(Utente entity) {

    }
}
