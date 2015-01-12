package org.oop.db;


import java.util.HashMap;


/**
 * SQLParameters &egrave; un'estensione di HashMap<String,Object>. Il suo compito primario &egrave; quello di fornire un
 * blueprint unico e usufruibile in tutto il codice per rappresentare i parametri da sostituire in una espressione SQL
 * parametrica.
 * <p/>
 * La chiave rappresenta il nome del placeholder (vedere setParameters), il valore rappresenta il valore che deve essere
 * sostituito al placeholder
 * <p/>
 * Notazione: con la scrittura "id"->3 si intende un elemento di SQLParameters che lega alla chiave "id" l'intero 3.
 *
 * @see DatabaseManager#setParameters
 * @see java.util.HashMap
 */
public class SQLParameters extends HashMap<String, Object> {

    /**
     * Aggiunge una coppia chiave-valore
     *
     * @param key   Nome della chiave
     * @param value Oggetto valore
     * @return Istanza corrente dell'oggetto SQLParameters
     */
    public SQLParameters add(String key, Object value) {
        this.put(key, value);
        return this;
    }

    /**
     * Unisce gli elementi di due oggetti SQLParameters
     *
     * @param parameters Oggetto da unire
     * @return Instanza dell'oggetto da cui si è chiamato union, con i nuovi elementi
     */
    public SQLParameters union(SQLParameters parameters) {
        for (SQLParameters.Entry<String, Object> param : parameters.entrySet()) {
            add(param.getKey(), param.getValue());
        }
        return this;
    }
}
