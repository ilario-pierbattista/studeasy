package org.oop.db;


import java.util.HashMap;

public class SQLParameters extends HashMap<String, Object> {

    public SQLParameters add(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public SQLParameters merge(SQLParameters parameters) {
        for (SQLParameters.Entry<String, Object> param : parameters.entrySet()) {
            add(param.getKey(), param.getValue());
        }
        return this;
    }
}
