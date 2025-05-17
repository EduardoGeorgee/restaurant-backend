package com.restfood.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null || conn.isClosed()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new LoginResponse(false, "Error de conexión", null));
            }

            String sql = "SELECT R.nombre AS rol FROM Empleados E " +
                    "JOIN Roles R ON E.id_rol = R.id_rol " +
                    "WHERE E.correo = ? AND E.contrasena = ?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, request.getCorreo());
                pst.setString(2, request.getContrasena());

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String rol = rs.getString("rol");
                        return ResponseEntity.ok(new LoginResponse(true, "Login exitoso", rol));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(new LoginResponse(false, "Credenciales inválidas", null));
                    }
                }
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse(false, "Error: " + e.getMessage(), null));
        }
    }

}
