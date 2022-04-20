package com.example.lab3grupo1.entity;

import javax.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcuenta", nullable = false)
    private Integer id;

    @Column(name = "correo", length = 45)
    private String correo;

    @Column(name = "direccion", nullable = false, length = 80)
    private String direccion;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "admin")
    private Integer admin;

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}