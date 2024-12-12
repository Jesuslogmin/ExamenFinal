package com.barturen.validator;

import com.barturen.entity.Infraccion;
import com.barturen.exception.ValidateException;

import java.util.Date;

public class InfraccionValidator {

    public static void validate(Infraccion infraccion) {
        if (infraccion.getDni() == null || infraccion.getDni().trim().isEmpty()) {
            throw new ValidateException("El DNI es requerido");
        }
        if (infraccion.getDni().length() != 8 || !infraccion.getDni().matches("\\d+")) {
            throw new ValidateException("El DNI debe contener exactamente 8 dígitos numéricos");
        }

        if (infraccion.getPlaca() == null || infraccion.getPlaca().trim().isEmpty()) {
            throw new ValidateException("La placa es requerida");
        }
        if (!infraccion.getPlaca().matches("[A-Z0-9]{7}")) {
            throw new ValidateException("La placa debe tener 7 caracteres alfanuméricos en mayúsculas");
        }

        if (infraccion.getInfraccion() == null || infraccion.getInfraccion().trim().isEmpty()) {
            throw new ValidateException("El campo 'infracción' es requerido");
        }
        if (infraccion.getInfraccion().length() > 200) {
            throw new ValidateException("El campo 'infracción' no debe exceder los 200 caracteres");
        }

        if (infraccion.getDescripcion() != null && infraccion.getDescripcion().length() > 255) {
            throw new ValidateException("El campo 'descripción' no debe exceder los 255 caracteres");
        }

        if (infraccion.getFecha() == null) {
            throw new ValidateException("La fecha de la infracción es requerida");
        }
        if (infraccion.getFecha().after(new Date())) {
            throw new ValidateException("La fecha de la infracción no puede ser una fecha futura");
        }
    }
}
