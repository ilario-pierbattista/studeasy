package org.oop.db;


import org.oop.general.Utils;

import java.sql.*;
import java.time.LocalTime;

/**
 * Incapsula l'accesso alla connessione con il database, mettendo a disposizione i metodi necessari per effettuare
 * chiamate al db
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
        // il metodo getInstance() restituisce l'unica istanza della classe a cui è riferito
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
     * @param sql Istruzione SQL
     * @return Istanza di DatabaseManager
     */
    //lo statement è una sorta di connessione che si occupa di inviare le istruzioni al database(?)
    public DatabaseManager createSqlStatement(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * Rende accessibile dall'esterno uno statement SQL
     *
     * @return Ultima istruzione SQL immessa
     */
    public String getSqlStatement() {
        return sql;
    }

    /**
     * Sostituisce, in uno statement parametrico, i parametri con i rispettivi valori.
     * <p/>
     * Per statement parametrico si intende un'istruzione sql con dei placeholder nei punti dove andrebbero dei valori.
     * Ogni placeholder &egrave; identificato dai due punti (":") che lo precedono, ad esempio ":id".
     * <p/>
     * Il metodo sostituisce quei placeholder con i relativi valori definiti nell'oggetto SQLParameters. Ad ogni chiave
     * dell'oggetto SQLParameters, verr&agrave; cercato il relativo placeholder con lo stesso nome, quindi quest'ultimo
     * verr&agrave; sostituito con il relativo valore. Ad esempio, per un elemento* "id"->3 di SQLParameters, si
     * cercher&agrave; il placeholder ":id", sostituendolo con "3".
     * <p/>
     * * Consultare SQLParameters per informazioni sulla notazione
     *
     * @param params Oggetto SQLParameters con i parametri da sostituire
     * @return Istanza di DatabaseManager
     * @see SQLParameters
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
     * Ritorna l'oggetto ResultSet associato all'esecuzione dei una query a partire dall'istruzione sql impostata con
     * createSqlStatement
     *
     * @return Oggetto ResultSet collegato alla query
     * @see #createSqlStatement
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
            System.out.println(sql);
            ee.printStackTrace();
        }
        return rs;
    }

    /**
     * Esegue un'istruzione DML impostata con createSqlStatement
     *
     * @return Chiave autogenerata dal dbms in caso di nuovo inserimento
     * @see #createSqlStatement
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
     * Chiude la connessione con il database
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
     * Apre la connessione con il database specificando lo stato di Auto-Commit
     *
     * @param autoCommit True per abilitare l'autocommit, False altrimenti
     * @throws SQLException
     */
    private void openConnection(boolean autoCommit) throws SQLException {
        try {
            Class.forName(config.jdbc_driver);
            connection = DriverManager.getConnection(config.db_url, config.user, config.pass);
            connection.setAutoCommit(autoCommit);
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        }
    }
}
