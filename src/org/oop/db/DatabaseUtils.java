package org.oop.db;


import org.oop.general.Utils;
import org.oop.general.exceptions.RisorsaNonTrovata;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseUtils {

    /**
     * Genera una stringa di condizioni AND per una chiamata SQL in base alle coppie
     * parametro-valore passate.
     * @param parameters
     * @return
     */
    public static String generateAndCondition(SQLParameters parameters) {
        ArrayList<String> conditions = new ArrayList<String>(3);
        for(SQLParameters.Entry<String, Object> param : parameters.entrySet()) {
            conditions.add(param.getKey().concat(" = :".concat(param.getKey())));
        }
        return Utils.stringJoin(conditions, " AND ");
    }

    /**
     * Controlla l'esistenza di uno specifico database
     * @return
     */
    public static boolean databaseExists() {
        boolean exists = false;
        DatabaseConfig config = DatabaseConfig.getInstance();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs;
        try {
            Class.forName(config.jdbc_driver);
            connection = DriverManager.getConnection(config.host_url, config.user, config.pass);
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = " + Utils.singleQuotesToString(config.db_name));
            if(rs.next()) {
                exists = true;
            }
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }
        return exists;
    }

    /**
     * Esegue uno script SQL
     * @param scriptPath path relativa dello script nelle risorse
     * @throws RisorsaNonTrovata Lanciata nel caso in cui la risorsa non venga trovata
     */
    public static void execSQLScript(String scriptPath) throws RisorsaNonTrovata {
        ArrayList<String> lines = Utils.readFileLines(scriptPath);
        DatabaseConfig config = DatabaseConfig.getInstance();
        Connection connection = null;
        Statement statement = null;
        String commandDelimiter = ";";
        int lineNumber = 1;

        try {
            Class.forName(config.jdbc_driver);
            connection = DriverManager.getConnection(config.host_url, config.user, config.pass);
            statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            String trimmered;
            for(String line: lines) {
                trimmered = line.trim();
                // Costruisco il comando
                if(!trimmered.isEmpty()) {
                    sb.append(line);
                }
                // Se il comando termina con il delimiter, ottengo la stringa e lo eseguo
                if(trimmered.endsWith(commandDelimiter)) {
                    statement.executeUpdate(sb.toString());
                    // Pulisco lo StringBuilder
                    sb.setLength(0);
                }
                lineNumber++;
            }
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        } catch (SQLException ee) {
            System.out.println("Errore alla riga ".concat(Integer.toString(lineNumber)));
            ee.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }
    }
}
