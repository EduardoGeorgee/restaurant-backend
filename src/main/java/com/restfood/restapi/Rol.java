package com.restfood.restapi;

public class Rol {

    private int idRol;
    private String nombre;

    public Rol(int idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdRol() { return idRol; }
    public String getNombre() { return nombre; }
    public void setIdRol(int idRol) { this.idRol = idRol; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return nombre; // Para mostrar correctamente en el Spinner
    }
}
