package org.oop.db;


public class DatabaseConfig {
    private static DatabaseConfig instance;
    protected String jdbc_driver;
    protected String db_url;
    protected String db_name;
    protected String host_url;
    protected String user;
    protected String pass;

    public DatabaseConfig() {
        jdbc_driver = "com.mysql.jdbc.Driver";
        host_url = "jdbc:mysql://localhost";
        db_name = "oop_db";
        db_url = host_url.concat("/").concat(db_name);
        user = "root";
        pass = "pass";
        instance = this;
    }

    public static DatabaseConfig getInstance() {
        if(instance == null) {
            new DatabaseConfig();
        }
        return instance;
    }
}
