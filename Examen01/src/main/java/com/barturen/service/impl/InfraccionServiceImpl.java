package com.barturen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barturen.entity.Infraccion;
import com.barturen.exception.ValidateException;
import com.barturen.repository.InfraccionRepository;
import com.barturen.service.InfraccionService;
import com.barturen.validator.InfraccionValidator;

@Service
public class InfraccionServiceImpl implements InfraccionService {

    @Autowired
    private InfraccionRepository infraccionRepository;

    @Override
    public List<Infraccion> findAll(Pageable page) {
        return infraccionRepository.findAll(page).getContent();
    }

    @Override
    public List<Infraccion> findAll() {
        return infraccionRepository.findAll();
    }

    @Override
    public List<Infraccion> findByDni(String dni, Pageable page) {
        return infraccionRepository.findByDniContaining(dni);
    }

    @Override
    public List<Infraccion> findByPlaca(String placa, Pageable page) {
        return infraccionRepository.findByPlacaContaining(placa);
    }

    @Override
    public List<Infraccion> findByFecha(Date fecha) {
        return infraccionRepository.findByFecha(fecha);
    }

    @Override
    public Infraccion findById(int id) {
        return infraccionRepository.findById(id).orElse(null);
    }

    @Override
    public Infraccion save(Infraccion infraccion) {
        try {
            InfraccionValidator.validate(infraccion);
        } catch (ValidateException e) {
            throw new RuntimeException("Error de validación: " + e.getMessage());
        }
        return infraccionRepository.save(infraccion);
    }

    @Override
    public void delete(int id) {
        if (!infraccionRepository.existsById(id)) {
            throw new RuntimeException("La infracción con ID " + id + " no existe");
        }
        infraccionRepository.deleteById(id);
    }
}
