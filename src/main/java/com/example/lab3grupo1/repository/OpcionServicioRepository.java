package com.example.lab3grupo1.repository;

import com.example.lab3grupo1.dto.PrecioDto;
import com.example.lab3grupo1.entity.OpcionServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpcionServicioRepository extends JpaRepository<OpcionServicio, Integer> {

}