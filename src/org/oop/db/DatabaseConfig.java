package org.oop.db;


public class DatabaseConfig {
    private static DatabaseConfig instance;
    protected String jdbc_driver;
    protected String db_url;
    protected String user;
    protected String pass;

    public DatabaseConfig() {
        jdbc_driver = "com.mysql.jdbc.Driver";
        db_url = "jdbc:mysql://localhost/oop_db";
        user = "root";
        pass = "pass";
        instance = this;
    }

    public static DatabaseConfig getInstance() {
        return instance;
    }
}
