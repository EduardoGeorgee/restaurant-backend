package com.restfood.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        logger.info("Intentando iniciar sesión con el correo: {}", request.getCorreo());

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null || conn.isClosed()) {
                logger.error("No se pudo establecer la conexión con la base de datos.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new LoginResponse(false, "Error de conexión", null));
            }

            String sql = "SELECT R.nombre AS rol FROM Empleados E " +
                    "JOIN Roles R ON E.id_rol = R.id_rol " +
                    "WHERE E.correo = ? AND E.contrasena = ?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, request.getCorreo());
                pst.setString(2, request.getContrasena());

                logger.info("Ejecutando consulta SQL para verificar credenciales...");
                logger.debug("Correo: {}, Contraseña: {}", request.getCorreo(), request.getContrasena());

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String rol = rs.getString("rol");
                        logger.info("Autenticación exitosa. Rol: {}", rol);
                        return ResponseEntity.ok(new LoginResponse(true, "Login exitoso", rol));
                    } else {
                        logger.warn("Credenciales inválidas para el correo: {}", request.getCorreo());
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new LoginResponse(false, "Credenciales inválidas", null));
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error durante la autenticación: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse(false, "Error: " + e.getMessage(), null));
        }
    }
}
