package com.restfood.restapi;

public class LoginRequest {

    private String correo;
    private String contrasena;

    public LoginRequest(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {return correo;}
    public String getContrasena() {return contrasena;}


}
