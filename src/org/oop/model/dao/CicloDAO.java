package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.Attivita;
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
        try {
            ciclo.setId(rs.getInt("id"))
                    .setLabel(rs.getString("label"))
                    .setInizio(rs.getDate("inizio"))
                    .setFine(rs.getDate("fine"));
            ciclo.setInsegnamenti(getInsegnamentiCiclo(ciclo));
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
        entity.setId(id);
        updateInsegnamentiRelationship(entity);
        updateAttivitaRelationship(entity);
    }

    @Override
    public void update(Ciclo entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("UPDATE ciclo " +
                "SET label = :label, inizio = :inizio, fine = :fine " +
                "WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
        updateInsegnamentiRelationship(entity);
        updateAttivitaRelationship(entity);
    }

    @Override
    public void remove(Ciclo entity) {
        SQLParameters parameters = generaSQLParams(entity);
        db.createSqlStatement("DELETE FROM ciclo WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(0);
    }

    private ArrayList<Insegnamento> getInsegnamentiCiclo(Ciclo ciclo) {
        InsegnamentoDAO insegnamentoDAO = new InsegnamentoDAO();
        ArrayList<Insegnamento> insegnamenti = new ArrayList<Insegnamento>(1);
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", ciclo.getId());
        ResultSet rs = db.createSqlStatement("SELECT * FROM iu_ciclo WHERE ciclo = :id")
                .setParameters(parameters)
                .getResult();
        try {
            ArrayList<Integer> idInsegnamenti = new ArrayList<Integer>(3);
            while(rs.next()) {
                idInsegnamenti.add(rs.getInt("insegnamento_utente"));
            }
            rs.close();
            if(!idInsegnamenti.isEmpty()) {
                insegnamenti = insegnamentoDAO.findBy(new SQLParameters().add("id", idInsegnamenti));
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        for(Insegnamento insegnamento : insegnamenti) {
            ArrayList<Attivita> attivita = getAttivitaPerInsegnamentoCiclo(ciclo, insegnamento);
            if(attivita != null && !attivita.isEmpty()) {
                insegnamento.setAttivita(attivita);
            }
        }
        return insegnamenti;
    }

    private ArrayList<Attivita> getAttivitaPerInsegnamentoCiclo(Ciclo ciclo, Insegnamento insegnamento) {
        AttivitaDAO attivitaDAO = new AttivitaDAO();
        SQLParameters parameters = new SQLParameters();
        parameters.add("ciclo", ciclo.getId())
                .add("insegnamento_utente", insegnamento.getId());
        return attivitaDAO.findBy(parameters);
    }

    private void updateInsegnamentiRelationship(Ciclo ciclo) {
        db.createSqlStatement("DELETE FROM iu_ciclo WHERE ciclo = :ciclo")
                .setParameters(new SQLParameters().add("ciclo", ciclo.getId()))
                .executeUpdate();
        for(Insegnamento insegnamento : ciclo.getInsegnamenti()) {
            SQLParameters insPar = new SQLParameters();
            insPar.add("ciclo", ciclo.getId())
                    .add("insegnamento_utente", insegnamento.getId());
            db.createSqlStatement("INSERT INTO iu_ciclo (ciclo, insegnamento_utente) " +
                    "VALUES (:ciclo, :insegnamento_utente)")
                    .setParameters(insPar)
                    .executeUpdate();
        }
    }

    private void updateAttivitaRelationship(Ciclo ciclo) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("ciclo", ciclo.getId());
        for (Insegnamento insegnamento : ciclo.getInsegnamenti()) {
            parameters.add("insegnamento_utente", insegnamento.getId());
            for (Attivita attivita : insegnamento.getAttivita()) {
                parameters.add("attivita", attivita.getId());
                db.createSqlStatement("UPDATE attivita " +
                        "SET ciclo = :ciclo, insegnamento_utente = :insegnamento_utente " +
                        "WHERE id = :attivita")
                        .setParameters(parameters)
                        .executeUpdate();
            }
        }
    }
}
