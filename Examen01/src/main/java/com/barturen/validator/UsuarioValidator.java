package com.barturen.validator;

import com.barturen.entity.Usuario;
import com.barturen.exception.ValidateException;

public class UsuarioValidator {
	public static void save(Usuario registro) {
		if(registro.getEmail()==null || registro.getEmail().trim().isEmpty()) {
			throw new ValidateException("El email es requerido");
		}
		if(registro.getEmail().length()>70) {
			throw new ValidateException("El email no debe exceder los 70 caracteres");
		}
		if(registro.getPassword()==null || registro.getPassword().trim().isEmpty()) {
			throw new ValidateException("El password es requerido");
		}
	}
}
