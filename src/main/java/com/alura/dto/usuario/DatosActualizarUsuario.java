package com.alura.dto.usuario;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
		@NotNull 
		Long id,
		
		String nombre,
		
		@Email
		String email,
		
		@Length(min = 6)
		String contrasena) {

}
