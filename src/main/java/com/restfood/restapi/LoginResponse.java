package com.restfood.restapi;

public class LoginResponse {
    private boolean success;
    private String message;
    private String rol;

    public LoginResponse() {}

    public LoginResponse(boolean success, String message, String rol) {
        this.success = success;
        this.message = message;
        this.rol = rol;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
