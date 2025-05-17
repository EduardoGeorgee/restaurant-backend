package com.restfood.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;

@RestController
public class TestController {

    @GetMapping("/testdb")
    public String testDatabaseConnection() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                return "Conexión exitosa a la base de datos.";
            } else {
                return "No se pudo conectar a la base de datos.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al intentar conectar: " + e.getMessage();
        }
    }
}
