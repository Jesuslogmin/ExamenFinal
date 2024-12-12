package com.barturen.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.barturen.entity.Infraccion;

public interface InfraccionService {

	List<Infraccion> findAll(Pageable page);

	List<Infraccion> findAll();

	List<Infraccion> findByDni(String dni, Pageable page);

	List<Infraccion> findByPlaca(String placa, Pageable page);

	List<Infraccion> findByFecha(Date fecha);

	Infraccion findById(int id);

	Infraccion save(Infraccion infraccion);

	void delete(int id);
}
