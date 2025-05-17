package com.restfood.restapi;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String IP = "192.168.56.1";
    private static final String PORT = "50059";
    private static final String DATABASE = "Restaurant";
    private static final String USERNAME = "Fornite";
    private static final String PASSWORD = "Juan123";

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionString = String.format(
                    "jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false;user=%s;password=%s;",
                    IP, PORT, DATABASE, USERNAME, PASSWORD
            );

            conexion = DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
