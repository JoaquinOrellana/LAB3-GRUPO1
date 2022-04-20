package com.example.lab3grupo1.entity;

import javax.persistence.*;

@Entity
@Table(name="raza_especie")

public class Raza_especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idraza")
    private String idraza;

    @Column(name = "descripcion")
    private String descripcion;

    public String getIdraza() {
        return idraza;
    }

    public void setIdraza(String idraza) {
        this.idraza = idraza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
