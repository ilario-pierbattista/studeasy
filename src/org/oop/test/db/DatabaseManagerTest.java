package org.oop.test.db;

import org.oop.db.DatabaseManager;
import org.oop.db.SQLParameters;

import java.util.ArrayList;


public class DatabaseManagerTest {

    public void testSetParameters() throws TestFallito {
        DatabaseManager db = new DatabaseManager();
        SQLParameters params = new SQLParameters();
        ArrayList<String> result_expected = new ArrayList<String>(3);
        ArrayList<String> output = new ArrayList<String>(3);
        params.add("id", 1)
                .add("id_utente", 2)
                .add("stringa", "prova");

        db.createSqlStatement("SELECT * FROM table WHERE id = :id")
                .setParameters(params);
        result_expected.add(0, "SELECT * FROM table WHERE id = 1");
        output.add(0, db.getSqlStatement());

        db.createSqlStatement("SELECT * FROM table WHERE id = :id_utente AND utente = :id")
                .setParameters(params);
        result_expected.add(1, "SELECT * FROM table WHERE id = 2 AND utente = 1");
        output.add(1, db.getSqlStatement());

        db.createSqlStatement("INSERT INTO table (id,utente,valore) VALUES (:id,:id_utente,:stringa)")
                .setParameters(params);
        result_expected.add(2, "INSERT INTO table (id,utente,valore) VALUES (1,2,'prova')");
        output.add(2, db.getSqlStatement());

        db.closeConnection();

        for (int i = 0; i < result_expected.size(); i++) {
            if(!result_expected.get(i).equals(output.get(i))) {
                System.out.println("L'output " + output.get(i) + " e l'output atteso " + result_expected.get(i) +
                " non corrispondono");
                throw new TestFallito();
            }
        }
    }
}
