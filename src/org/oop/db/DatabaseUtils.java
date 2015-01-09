package org.oop.db;


import org.oop.general.Utils;
import org.oop.general.exceptions.RisorsaNonTrovata;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class DatabaseUtils {

    /**
     * Questa roba deve essere documentata alla perfezione
     * @param parameters
     * @return
     */
    public static String generateCondition(SQLParameters parameters) {
        ArrayList<String> condizioniAnd = new ArrayList<String>(3);
        ArrayList<String> chiaviDaEliminare = new ArrayList<String>(1);
        SQLParameters parametriAusiliari = new SQLParameters();
        String chiaveParametro;

        for(SQLParameters.Entry<String, Object> param : parameters.entrySet()) {
            if(param.getValue() instanceof ArrayList) {
                chiaviDaEliminare.add(param.getKey());
                ArrayList<String> condizioniOr = new ArrayList<String>(3);
                ArrayList valori = (ArrayList) param.getValue();
                for (int i = 0; i < valori.size(); i++) {
                    chiaveParametro = param.getKey().concat("_").concat(Integer.toString(i));
                    parametriAusiliari.add(chiaveParametro, valori.get(i));
                    condizioniOr.add(param.getKey().concat(" = :").concat(chiaveParametro));
                }
                condizioniAnd.add("(".concat(Utils.stringJoin(condizioniOr, " OR ")).concat(")"));
            } else {
                condizioniAnd.add(param.getKey().concat(" = :".concat(param.getKey())));
            }
        }

        for (String chiave : chiaviDaEliminare) {
            parameters.remove(chiave);
        }
        parameters.merge(parametriAusiliari);

        return Utils.stringJoin(condizioniAnd, " AND ");
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
        //oggetto che permette di recuperare le info ottenute con una query (ResultSet)
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

    /**
     * Crea, da un oggetto java.util.Date, una stringa adatta
     * ad una chiamata SQL
     * @param date
     * @return
     */
    protected static String getDateStringForSQL(java.util.Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    protected static String getTimeStringForSQL(LocalTime time) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(time);
    }
}
