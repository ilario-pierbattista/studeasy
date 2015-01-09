package org.oop.db;


import org.oop.general.Utils;

import java.sql.*;
import java.time.LocalTime;

/**
 * Incapsula l'accesso alla connessione, mettendo a disposizione
 * i metodi necessari per effettuare chiamate al database
 */
public class DatabaseManager {

    private static DatabaseManager instance;
    private DatabaseConfig config;
    private String sql;
    private Connection connection;

    /**
     * Configurazione del manager
     */
    public DatabaseManager() {
        //il metodo getInstance() restituisce l'unica istanza della classe a cui è riferito
        config = DatabaseConfig.getInstance();
        instance = this;
    }

    /**
     * Ritorna l'istanza attiva della classe
     *
     * @return Istanza di DatabaseManager
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            new DatabaseManager();
        }
        return instance;
    }

    /**
     * Imposta il contenuto dello statement SQL da inviare al server
     *
     * @param sql
     * @return
     */
    //lo statement è una sorta di connessione che si occupa di inviare le istruzioni al database(?)
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
    public DatabaseManager setParameters(SQLParameters params) {
        String replace = null, regex;
        for (SQLParameters.Entry<String, Object> param : params.entrySet()) {
            // Conversione dei valori dei parametri in stringhe adatte a query sql
            if (param.getValue() instanceof Integer ||
                    param.getValue() instanceof Double ||
                    param.getValue() instanceof Float) {
                replace = param.getValue().toString();
            } else if (param.getValue() instanceof String) {
                replace = Utils.singleQuotesToString(Utils.escapeSql((String) param.getValue()));
            } else if (param.getValue() instanceof Boolean) {
                replace = (Boolean) param.getValue() ? "TRUE" : "FALSE";
            } else if (param.getValue() instanceof java.util.Date) {
                String dateString = DatabaseUtils.getDateStringForSQL((java.util.Date) param.getValue());
                replace = Utils.singleQuotesToString(dateString);
            } else if (param.getValue() instanceof LocalTime) {
                String timeString = ((LocalTime) param.getValue()).toString();
                replace = Utils.singleQuotesToString(timeString);
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
            if (connection == null || connection.isClosed()) {
                openConnection(false);
            }
            Statement statement = connection.createStatement();
            statement.closeOnCompletion();
            rs = statement.executeQuery(sql);
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return rs;
    }

    /**
     * Esegue un comando DML
     *
     * @return
     */
    public int executeUpdate() {
        int auto_generated_key = 0;
        try {
            //l'autocommit è di default impostata. (una commit-promessa- serve a terminare "l'azione" col database)
            if (connection == null || connection.isClosed() || connection.getAutoCommit()) {
                openConnection(false);
            }
            Statement statement = connection.createStatement();
            statement.closeOnCompletion();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.first()) {
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
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

    /**
     * Esegue una commit
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Esegue il rollback
     */
    //torna indietro all'ultimo salvataggio
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
     * Apre la connessione con il database permettendo di specificare lo stato di Auto-Commit
     *
     * @param autoCommit
     * @throws SQLException
     */
    private void openConnection(boolean autoCommit) throws SQLException {
        System.out.println("NEW CONNECTION");
        try {
            Class.forName(config.jdbc_driver);
            connection = DriverManager.getConnection(config.db_url, config.user, config.pass);
            connection.setAutoCommit(autoCommit);
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        }
    }
}
