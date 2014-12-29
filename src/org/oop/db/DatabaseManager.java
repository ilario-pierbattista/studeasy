package org.oop.db;


import org.oop.general.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;

/**
 * Incapsula l'accesso alla connessione, mettendo a disposizione
 * i metodi necessari per effettuare chiamate al database
 *
 * @TODO impostare l'autocommit a false per gli inserimenti per aumentare le performance
 */
public class DatabaseManager {

    private DatabaseConfig config;
    private String sql;
    private Connection connection;
    private Statement statement;

    /**
     * Configurazione del manager
     */
    public DatabaseManager() {
        config = DatabaseConfig.getInstance();
    }

    /**
     * Imposta il contenuto dello statement SQL da inviare al server
     *
     * @param sql
     * @return
     */
    public DatabaseManager createSqlStatement(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * Rende accessibile dall'esterno uno statement SQL
     *
     * @return
     */
    public String getSqlStatement() {
        return sql;
    }

    /**
     * Sostituisce, in uno statement parametrico, i parametri con i rispettivi valori
     * Pre Condizione: uno statement parametrico è del tipo "SELECT * FROM table WHERE id = :id"
     * dove il parametro da sostituire è ":id"
     *
     * @param params Map chiave-valore di nomi di parametri e valore
     * @return
     */
    public DatabaseManager setParameters(Map<String, Object> params) {
        String replace = null, regex;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            // Conversione dei valori dei parametri in stringhe adatte a query sql
            if (param.getValue() instanceof Integer ||
                    param.getValue() instanceof Double ||
                    param.getValue() instanceof Float) {
                replace = param.getValue().toString();
            } else if (param.getValue() instanceof String) {
                replace = Utils.singleQuotesToString(Utils.escapeSql((String) param.getValue()));
            } else if (param.getValue() instanceof Boolean) {
                replace = (Boolean) param.getValue() ? "TRUE" : "FALSE";
            } else if (param.getValue() == null) {
                replace = "NULL";
            }
            if (replace != null) {
                regex = "\\:".concat(param.getKey()).concat("( |$)");
                sql = sql.replaceAll(regex, replace.concat(" ")).trim();
                regex = "\\:".concat(param.getKey()).concat(",");
                sql = sql.replaceAll(regex, replace.concat(","));
                regex = "\\:".concat(param.getKey()).concat("[)]");
                sql = sql.replaceAll(regex, replace.concat(")"));
            }
            replace = null;
        }
        return this;
    }

    /**
     * Esegue una query e ritorna il risultato
     *
     * @return
     */
    public ResultSet getResult() {
        ResultSet rs = null;
        try {
            openConnection();
            rs = statement.executeQuery(sql);
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate() {
        int auto_generated_key = 0;
        try {
            if(connection == null || connection.isClosed() || connection.getAutoCommit()) {
                openConnection(false);
            }
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.first()) {
                auto_generated_key = generatedKeys.getInt("GENERATED_KEY");
            }
        } catch (SQLException ee) {
            System.out.println(sql);
            ee.printStackTrace();
        }
        return auto_generated_key;
    }

    /**
     * Chiude la connessione e le risorse aperte con il database
     */
    public void closeConnection() {
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

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Apre la connessione con il database
     *
     * @throws SQLException
     */
    private void openConnection() throws SQLException {
        openConnection(true);
    }

    /**
     * Apre la connessione con il database permettendo di specificare lo stato di Auto-Commit
     * @param autoCommit
     * @throws SQLException
     */
    private void openConnection(boolean autoCommit) throws SQLException {
        try {
            Class.forName(config.jdbc_driver);
            connection = DriverManager.getConnection(config.db_url, config.user, config.pass);
            connection.setAutoCommit(autoCommit);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        }
    }
}
