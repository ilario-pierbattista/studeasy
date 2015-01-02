package org.oop.model.dao;

import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.general.Utils;
import org.oop.model.Agenda;
import org.oop.model.Libretto;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UtenteDAO extends AbstractDAO<Utente> {
    @Override
    protected Utente generaEntita(ResultSet rs) {
        Utente utente = new Utente();
        CorsoDAO corsoDAO = new CorsoDAO();
        InsegnamentoDAO insegnamentoDAO = new InsegnamentoDAO();
        try {
            utente.setMatricola(rs.getInt("matricola"))
                    .setNome(rs.getString("nome"))
                    .setCognome(rs.getString("cognome"))
                    .setEmail(rs.getString("email"));
            Libretto libretto = new Libretto();
            Agenda agenda = new Agenda();
            libretto.setCorso(corsoDAO.find(rs.getInt("corso")));
            libretto.setInsegnamenti(insegnamentoDAO.findBy(new SQLParameters().add("utente", utente.getMatricola())));
            utente.setLibretto(libretto);


        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return utente;
    }

    @Override
    protected SQLParameters generaSQLParams(Utente e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("matricola", e.getMatricola())
                .add("nome", e.getNome())
                .add("cognome", e.getCognome())
                .add("email", e.getEmail())
                .add("corso", e.getLibretto().getCorso().getId());
        return parameters;
    }

    @Override
    public Utente find(int matricola) {
        Utente utente = null;
        SQLParameters parameters = new SQLParameters();
        parameters.add("matricola", matricola);
        ResultSet rs = db.createSqlStatement("SELECT * FROM utente WHERE matricola = :matricola")
                .setParameters(parameters)
                .getResult();
        try {
            if (rs.next()) {
                utente = generaEntita(rs);
            }
            rs.close();
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
