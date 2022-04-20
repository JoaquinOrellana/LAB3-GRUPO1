package com.example.lab3grupo1.repository;

import com.example.lab3grupo1.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota,Integer> {
}
