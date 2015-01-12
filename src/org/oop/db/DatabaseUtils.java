package org.oop.db;


import org.oop.general.Utils;
import org.oop.general.exceptions.RisorsaNonTrovata;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DatabaseUtils {

    /**
     * Genera una porzione di istruzione SQL che costrituisce un insieme di condizioni generate a partire da un oggetto
     * SQLParameters.
     * <p/>
     * Questa porzione di istruzione è pensata per essere concatenata ad una qualsiasi istruzione SQL dopo la parola
     * chiave "WHERE".
     * <p/>
     * Ogni elemento di SQLParameters viene convertito in una condizione, ognuna delle quali deve essere soddisfatta
     * (vengono legate dall'operatore logico AND).
     * <p/>
     * Nel caso in cui ad una chiave sia associato un ArrayList di n valori, vengono generate n condizioni, almeno una
     * delle quali deve essere soddisfatta (vengono legate dall'operatore logico OR).
     * <p/>
     * Esempio: se l'oggetto SQLParameters ha la struttura* <code> "chiave1"->valore1; "chiave2"-> [ valore2_1,
     * valore2_2 ] </code>, il risultato di sarà della forma: <code>"chiave1 = :chiave1 AND (chiave2_1 = :chiave2_1 OR
     * chiave2_2 = :valore2_2)"</code> e l'oggetto SQLParameters sarà modificato assumendo una struttura di questo tipo:
     * <code>"chiave1"->valore1; "chiave2_1"->valore2_1; "chiave2_2"->valore2_2</code>.
     * <p/>
     * * Consultare SQLParameters per informazioni sulla notazione
     *
     * @param parameters Oggetto SQLParameters
     * @return Stringa con le condizioni parametriche generate
     * @see org.oop.db.SQLParameters
     * @see org.oop.db.DatabaseManager#setParameters
     */
    public static String generateCondition(SQLParameters parameters) {
        ArrayList<String> condizioniAnd = new ArrayList<String>(3);
        ArrayList<String> chiaviDaEliminare = new ArrayList<String>(1);
        SQLParameters parametriAusiliari = new SQLParameters();
        String chiaveParametro;

        for (SQLParameters.Entry<String, Object> param : parameters.entrySet()) {
            if (param.getValue() instanceof ArrayList) {
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
        parameters.union(parametriAusiliari);

        return Utils.stringJoin(condizioniAnd, " AND ");
    }

    /**
     * Controlla l'esistenza di uno specifico database
     *
     * @return True se il database esiste, False altrimenti
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
            if (rs.next()) {
                exists = true;
            }
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
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
     *
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
            for (String line : lines) {
                trimmered = line.trim();
                // Costruisco il comando
                if (!trimmered.isEmpty()) {
                    sb.append(line);
                }
                // Se il comando termina con il delimiter, ottengo la stringa e lo eseguo
                if (trimmered.endsWith(commandDelimiter)) {
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
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }
    }

    /**
     * Crea, da un oggetto java.util.Date, una stringa adatta ad una chiamata SQL
     *
     * @param date Data
     * @return Stringa che rappresenta la data nel formato adatto per essere inserita in un'istruzione SQL
     */
    protected static String getDateStringForSQL(java.util.Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
}
