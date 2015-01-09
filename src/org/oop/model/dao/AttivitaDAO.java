package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttivitaDAO extends AbstractDAO<Attivita> {
    @Override
    protected SQLParameters generaSQLParams(Attivita e) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", e.getId())
                .add("luogo", e.getLuogo())
                .add("ora_inizio", e.getOraInizio())
                .add("ora_fine", e.getOraFine())
                .add("docente", e.getDocente().getId())
                .add("ruolo_docente", e.getRuoloDocente())
                .add("categoria", e.getCategoria());
        if (e instanceof AttivitaPeriodica) {
            parameters.add("ripetizione_settimanale", true);
            parameters.add("giorno", ((AttivitaPeriodica) e).getGiorno());
            parameters.add("data", null);
            parameters.add("tipologia_prova", null);
        } else if (e instanceof AttivitaEvento) {
            parameters.add("ripetizione_settimanale", false);
            parameters.add("giorno", null);
            parameters.add("data", ((AttivitaEvento) e).getData());
            if (e instanceof Esame) {
                parameters.add("tipologia_prova", ((Esame) e).getTipologiaProva());
            } else {
                parameters.add("tipologia_prova", null);
            }
        }
        return parameters;
    }

    @Override
    protected Attivita generaEntita(ResultSet rs) {
        Attivita attivita = null;
        try {
            String categoria = rs.getString("categoria");
            boolean periodica = rs.getBoolean("ripetizione_settimanale");
            if(periodica) {
                attivita = new AttivitaPeriodica();
            } else {
                if(categoria.equals(Attivita.CATEGORIA_ESAME)) {
                    attivita = new Esame();
                } else {
                    attivita = new AttivitaEvento();
                }
            }
            attivita.setId(rs.getInt("id"))
                    .setOraInizio(rs.getTime("ora_inizio").toLocalTime())
                    .setOraFine(rs.getTime("ora_fine").toLocalTime())
                    .setLuogo(rs.getString("luogo"))
                    .setCategoria(categoria)
                    .setRuoloDocente(rs.getString("ruolo_docente"));
            DocenteDAO docenteDAO = new DocenteDAO();
            Docente docente = null;
            if(rs.getInt("docente") != 0) {
                docente = docenteDAO.find(rs.getInt("docente"));
            }
            attivita.setDocente(docente);
            if(attivita instanceof AttivitaPeriodica) {
                ((AttivitaPeriodica) attivita).setGiorno(rs.getInt("giorno"));
            } else {
                ((AttivitaEvento) attivita).setData(rs.getDate("data"));
                if(attivita instanceof Esame) {
                    ((Esame) attivita).setTipologiaProva(rs.getString("tipologia_prova"));
                }
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return attivita;
    }

    @Override
    public Attivita find(int id) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM attivita WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        Attivita attivita = null;
        try {
            if(rs.next()) {
                attivita = generaEntita(rs);
            }
            rs.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return attivita;
    }

    @Override
    public ArrayList<Attivita> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM attivita")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<Attivita> findBy(SQLParameters params) {
        String sql = "SELECT * FROM attivita WHERE ".concat(DatabaseUtils.generateCondition(params));
        ResultSet rs = db.createSqlStatement(sql)
                .setParameters(params)
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(Attivita entity) {
        SQLParameters parameters = generaSQLParams(entity);
        String sql = "INSERT INTO attivita" +
                "(ora_inizio, ora_fine, luogo, categoria, ripetizione_settimanale, " +
                "giorno, data, tipologia_prova, docente, ruolo_docente) " +
                "VALUES (:ora_inizio, :ora_fine, :luogo, :categoria, :ripetizione_settimanale, " +
                ":giorno, :data, :tipologia_prova, :docente, :ruolo_docente)";
        int id = db.createSqlStatement(sql)
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(id);
    }

    @Override
    public void update(Attivita entity) {
        SQLParameters parameters = generaSQLParams(entity);
        String sql = "UPDATE attivita " +
                "SET ora_inizio = :ora_inizio, ora_fine = :ora_fine, luogo = :luogo, categoria = :categoria " +
                "ripetizione_settimanale = :ripetizione_settimanale, giorno = :giorno, data = :data " +
                "tipologia_prova = :tipologia_prova, docente = :docente, ruolo_docente = :ruolo_docente";
        db.createSqlStatement(sql)
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(Attivita entity) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", entity.getId());
        db.createSqlStatement("DELETE FROM attivita WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }
}
