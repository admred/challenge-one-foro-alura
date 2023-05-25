package com.alura.foro.dto.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
		
		String nombre,
		
		String categoria) {

}
