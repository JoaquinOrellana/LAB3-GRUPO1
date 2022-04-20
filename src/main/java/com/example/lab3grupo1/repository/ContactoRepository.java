package com.example.lab3grupo1.repository;

import com.example.lab3grupo1.dto.ServiciosMascotaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Cuenta, Integer> {
    @Query(nativeQuery = true, value = "select * from cuenta")
    List<Cuenta> listaCuenta (Integer idcuenta);
}
