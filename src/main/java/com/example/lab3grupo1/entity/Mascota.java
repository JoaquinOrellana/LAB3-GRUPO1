package com.example.lab3grupo1.entity;

import javax.persistence.*;


@Entity
@Table(name="mascota")
public class Mascota{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "idmascota")
    private int idmascota;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "anho")
    private String anho;

    @Column(name = "historia")
    private String historia;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "sexo")
    private String sexo;
    @Column(name = "raza_otros")
    private String raza_otros;



    @ManyToOne
    @JoinColumn(name = "raza_especie_idraza")
    private Raza_especie raza_especie;


    @ManyToOne
    @JoinColumn(name = "cuenta_idcuenta")
    private Raza_especie cuenta_idcuenta;



    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaza_otros() {
        return raza_otros;
    }

    public void setRaza_otros(String raza_otros) {
        this.raza_otros = raza_otros;
    }

    public Raza_especie getRaza_especie() {
        return raza_especie;
    }

    public void setRaza_especie(Raza_especie raza_especie) {
        this.raza_especie = raza_especie;
    }

    public Raza_especie getCuenta_idcuenta() {
        return cuenta_idcuenta;
    }

    public void setCuenta_idcuenta(Raza_especie cuenta_idcuenta) {
        this.cuenta_idcuenta = cuenta_idcuenta;
    }
}

