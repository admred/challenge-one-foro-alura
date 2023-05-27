package com.alura.foro.dto.usuario;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(		
		@NotBlank
		String login,
		
		@NotBlank
		String nombre,
		
		@NotBlank
		@Email
		String email,
		
		@NotBlank
		@Length(min = 6)
		String contrasena
		
		) {

}
