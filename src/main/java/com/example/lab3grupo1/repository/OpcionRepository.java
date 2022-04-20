package com.example.lab3grupo1.repository;

import com.example.lab3grupo1.dto.PrecioDto;
import com.example.lab3grupo1.entity.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpcionRepository extends JpaRepository<Opcion, Integer> {
    @Query(value="select p.precio as precio FROM opcion p\n" +
            "inner join opcion_servicio s on (p.idopcion = s.idopcion_servicio)\n" +
            "inner join servicio es on (s.servicio_idservicio = es.idservicio)\n" +
            "group by p.precio\n", nativeQuery = true)
    List<PrecioDto> obtenerPrecios();
}