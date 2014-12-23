package org.oop.model.dao;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;
import org.oop.model.entities.Docente;
import org.oop.model.entities.InsegnamentoOfferto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @TODO FINIRE
 */
public class InsegnamentoOffertoDAO extends AbstractDAO<InsegnamentoOfferto> {

    @Override
    public InsegnamentoOfferto find(int id) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", id);
        ResultSet rs = db.createSqlStatement("SELECT * FROM insegnamento WHERE id = :id")
                .setParameters(parameters)
                .getResult();
        InsegnamentoOfferto ins = null;
        try {
            if (rs.first()) {
                ins = generaEntita(rs);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            db.closeConnection();
        }
        return ins;
    }

    @Override
    public ArrayList<InsegnamentoOfferto> findAll() {
        ResultSet rs = db.createSqlStatement("SELECT * FROM insegnamento")
                .getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public ArrayList<InsegnamentoOfferto> findBy(SQLParameters params) {
        String sql = "SELECT * FROM insegnamento " +
                "WHERE ".concat(DatabaseUtils.generateAndCondition(params));
        ResultSet rs = db.createSqlStatement(sql).setParameters(params).getResult();
        return generaArrayEntita(rs);
    }

    @Override
    public void persist(InsegnamentoOfferto ins) {
        SQLParameters params = new SQLParameters();
        params.add("nome", ins.getNome())
                .add("cfu", ins.getCfu())
                .add("anno", ins.getAnno())
                .add("semestre", ins.getSemestre())
                .add("opzionale", ins.isOpzionale());
        if (ins.getDocente() != null) {
            params.add("docente", ins.getDocente().getId());
        }
        int id = db.createSqlStatement("INSERT INTO insegnamento (nome,cfu,anno,semestre,opzionale,docente)" +
                "VALUES (:nome, :cfu, :anno, :semestre, :opzionale, :docente)")
                .setParameters(params)
                .executeUpdate();
        ins.setId(id);
    }

    @Override
    public void update(InsegnamentoOfferto entity) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", entity.getId())
                .add("nome", entity.getNome())
                .add("cfu", entity.getCfu())
                .add("anno", entity.getAnno())
                .add("semestre", entity.getSemestre())
                .add("opzionale", entity.isOpzionale());
        if(entity.getDocente() != null) {
            parameters.add("docente", entity.getDocente().getId());
        } else {
            parameters.add("docente", null);
        }
        db.createSqlStatement("UPDATE insegnamento " +
                "SET nome = :nome, cfu = :cfu, anno = :anno, semestre = :semestre, " +
                "opzionale = :opzionale, docente = :docente WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
    }

    @Override
    public void remove(InsegnamentoOfferto entity) {
        SQLParameters parameters = new SQLParameters();
        parameters.add("id", entity.getId());
        db.createSqlStatement("DELETE FROM insegnamento WHERE id = :id")
                .setParameters(parameters)
                .executeUpdate();
        entity.setId(0);
    }

    @Override
    protected InsegnamentoOfferto generaEntita(ResultSet rs) {
        InsegnamentoOfferto ins = new InsegnamentoOfferto();
        try {
            ins.setId(rs.getInt("id"))
                    .setNome(rs.getString("nome"))
                    .setAnno(rs.getInt("anno"))
                    .setSemestre(rs.getInt("semestre"))
                    .setCfu(rs.getInt("cfu"))
                    .setOpzionale(rs.getBoolean("opzionale"));
            DocenteDAO docenteDAO = new DocenteDAO();
            Docente docente = docenteDAO.find(rs.getInt("docente"));
            ins.setDocente(docente);
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return ins;
    }
}
