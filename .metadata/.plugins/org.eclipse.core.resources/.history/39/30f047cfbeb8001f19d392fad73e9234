package com.barturen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barturen.entity.Equipo;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    List<Equipo> findByNombreContaining(String nombre, org.springframework.data.domain.Pageable page);
    List<Equipo> findByCiudadContaining(String ciudad, org.springframework.data.domain.Pageable page);
}