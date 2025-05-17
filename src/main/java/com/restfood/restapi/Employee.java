package com.restfood.restapi;

import jakarta.persistence.*;

@Entity
@Table(name = "Empleados")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    private String correo;

    private String contrasena;

    @Column(name = "id_rol")
    private int idRol;

    private String telefono;

    // Constructor vacío requerido por JPA
    public Employee() {}

    // Constructor con parámetros
    public Employee(Long idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno,
                    String correo, String contrasena, int idRol, String telefono) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idRol = idRol;
        this.telefono = telefono;
    }

    // Getters y setters...

    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
}
