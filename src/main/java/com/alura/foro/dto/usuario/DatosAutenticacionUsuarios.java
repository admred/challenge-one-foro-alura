package com.alura.foro.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuarios(
		
		@Schema(type="string",defaultValue= "admin")
		@NotBlank
		String login,
		
		@Schema(type="string",defaultValue= "123456")
		@NotBlank
		String contrasena) {

}
