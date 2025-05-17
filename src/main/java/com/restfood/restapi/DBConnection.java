package com.restfood.restapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.net.URI;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;

        try {
            String dbUrl = System.getenv("DATABASE_URL");

            if (dbUrl == null || dbUrl.isEmpty()) {
                throw new IllegalArgumentException("DATABASE_URL not set");
            }

            // Parsear la URI del tipo: postgres://user:password@host:port/dbname
            URI dbUri = new URI(dbUrl);

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrlFormatted = String.format("jdbc:postgresql://%s:%d%s",
                    dbUri.getHost(),
                    dbUri.getPort(),
                    dbUri.getPath());

            // Establecer conexión
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbUrlFormatted, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
