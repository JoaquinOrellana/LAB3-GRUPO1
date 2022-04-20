package com.example.lab3grupo1.repository;


import com.example.lab3grupo1.entity.Raza_especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaRepository  extends JpaRepository<Raza_especie,Integer> {
}
