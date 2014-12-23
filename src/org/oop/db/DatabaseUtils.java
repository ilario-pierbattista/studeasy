package org.oop.db;


import org.oop.general.Utils;

import java.util.ArrayList;
import java.util.Vector;

public class DatabaseUtils {

    public static String generateAndCondition(SQLParameters parameters) {
        ArrayList<String> conditions = new ArrayList<String>(3);
        for(SQLParameters.Entry<String, Object> param : parameters.entrySet()) {
            conditions.add(param.getKey().concat(" = :".concat(param.getKey())));
        }
        return Utils.stringJoin(conditions, " AND ");
    }
}
