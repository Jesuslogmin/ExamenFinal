package com.barturen.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.barturen.entity.Carrera;

public interface CarreraService {
	List<Carrera> findAll(Pageable page);

	List<Carrera> findAll();

	List<Carrera> findByNombre(String nombre, Pageable page);

	Carrera findById(int id);

	Carrera save(Carrera carrera);

	void delete(int id);
}
