package org.oop.test.db;


import org.oop.db.DatabaseUtils;
import org.oop.db.SQLParameters;

import java.util.ArrayList;

public class DatabaseUtilsTest {

    public void testGenerateCondition() throws TestFallito {
        SQLParameters parameters = new SQLParameters();
        ArrayList<String> valori = new ArrayList<String>(3);
        String conditions;

        parameters.add("campo1", "valore")
                .add("campo2", 1)
                .add("campo3", false);
        valori.add("valore1");
        valori.add("valore2");
        valori.add("valore3");
        parameters.add("campo4", valori);

        conditions = DatabaseUtils.generateCondition(parameters);
        String expecetedOutput = "campo1 = :campo1 AND campo3 = :campo3 AND campo2 = :campo2 " +
                "AND (campo4 = :campo4_0 OR campo4 = :campo4_1 OR campo4 = :campo4_2)";
        if(!expecetedOutput.equals(conditions)) {
            System.out.println("Output atteso: ".concat(expecetedOutput).concat("\nOutput ricevuto: ").concat(conditions));
            throw new TestFallito();
        }
    }
}
