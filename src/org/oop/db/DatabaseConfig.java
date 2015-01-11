package org.oop.db;


/**
 * Fornisce i dati necessari per stabilire una connessione con il database
 */
public class DatabaseConfig {
    private static DatabaseConfig instance;
    protected String jdbc_driver;
    protected String db_url;
    protected String db_name;
    protected String host_url;
    protected String user;
    protected String pass;

    /**
     * Inizializza gli attributi con i valori di default
     */
    public DatabaseConfig() {
        jdbc_driver = "com.mysql.jdbc.Driver";
        host_url = "jdbc:mysql://localhost";
        db_name = "oop_db";
        db_url = host_url.concat("/").concat(db_name);
        user = "root";
        pass = "pass";
        instance = this;
    }

    /**
     * Ritorna l'istanza attiva di DatabaseConfig. Se non esiste alcuna istanza, viene creata.
     *
     * @return Istanza di DatabaseConfig
     */
    public static DatabaseConfig getInstance() {
        if (instance == null) {
            new DatabaseConfig();
        }
        return instance;
    }

    /**
     * Modifica l'attributo user per la connessione al database
     *
     * @param user Username per l'accesso al database
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Modifica l'attributo pass per la connessione al database
     *
     * @param pass Password per la connessione al database
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
