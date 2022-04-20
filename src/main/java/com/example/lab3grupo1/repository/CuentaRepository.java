package com.example.lab3grupo1.repository;

import com.example.lab3grupo1.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
}