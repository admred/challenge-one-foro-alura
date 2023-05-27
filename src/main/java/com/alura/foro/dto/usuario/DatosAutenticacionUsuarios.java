package com.alura.foro.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuarios(
		@NotBlank
		String login,
		
		@NotBlank
		String contrasena) {

}
