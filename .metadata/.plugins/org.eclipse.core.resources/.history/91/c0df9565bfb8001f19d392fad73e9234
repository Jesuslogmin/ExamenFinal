package com.barturen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barturen.entity.Equipo;
import com.barturen.repository.EquipoRepository;
import com.barturen.service.EquipoService;
import com.barturen.validator.EquipoValidator;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Equipo> findAll(Pageable page) {
        return equipoRepository.findAll(page).getContent();
    }

    @Override
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    @Override
    public List<Equipo> findByNombre(String nombre, Pageable page) {
        return equipoRepository.findByNombreContaining(nombre, page);
    }

    @Override
    public List<Equipo> findByCiudad(String ciudad, Pageable page) {
        return equipoRepository.findByCiudadContaining(ciudad, page);
    }

    @Override
    public Equipo findById(int id) {
        return equipoRepository.findById(id).orElse(null);
    }

    @Override
    public Equipo save(Equipo equipo) {
    	if (equipo.getNombre() == null || equipo.getNombre().trim().isEmpty()) {
            EquipoValidator.validate(equipo);
        }
        return equipoRepository.save(equipo);
    }

    @Override
    public void delete(int id) {
        equipoRepository.deleteById(id);
    }
}
