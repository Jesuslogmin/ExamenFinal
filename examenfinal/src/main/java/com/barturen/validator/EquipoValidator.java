package com.barturen.validator;

import com.barturen.entity.Equipo;
import com.barturen.exception.ValidateException;

public class EquipoValidator {
    public static void validate(Equipo equipo) {
        if (equipo.getNombre() == null || equipo.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre del equipo es requerido");
        }
        if (equipo.getNombre().length() > 100) {
            throw new ValidateException("El nombre del equipo no debe tener más de 100 caracteres");
        }

        if (equipo.getCiudad() == null || equipo.getCiudad().trim().isEmpty()) {
            throw new ValidateException("La ciudad del equipo es requerida");
        }
        if (equipo.getCiudad().length() > 100) {
            throw new ValidateException("La ciudad del equipo no debe tener más de 100 caracteres");
        }

        if (equipo.getNumeroJugadores() <= 0) {
            throw new ValidateException("El número de jugadores debe ser mayor a 0");
        }
    }
}
