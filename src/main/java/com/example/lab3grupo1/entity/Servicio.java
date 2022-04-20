package com.example.lab3grupo1.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mascota_idmascota", nullable = false)
    private Mascota idmascota;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "cuenta_idcuenta", nullable = false)
//    private Cuenta idcuenta;

    @Column(name = "hora_inicio", nullable = false)
    private Instant horainicio;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Lob
    @Column(name = "entrega", nullable = false)
    private String entrega;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "responsable_idresponsable", nullable = false)
    private Responsable idresponsable;

    public Responsable getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(Responsable idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Instant getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Instant horainicio) {
        this.horainicio = horainicio;
    }

//    public Cuenta getIdcuenta() {
//        return idcuenta;
//    }

//    public void setIdcuenta(Cuenta idcuenta) {
//        this.idcuenta = idcuenta;
//    }

    public Mascota getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(Mascota idmascota) {
        this.idmascota = idmascota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}