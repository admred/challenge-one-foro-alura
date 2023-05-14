package com.alura.dto.topico;

import com.alura.dto.curso.DatosActualizarCurso;
import com.alura.dto.usuario.DatosActualizarUsuario;
import com.alura.modelo.Curso;
import com.alura.modelo.Usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
		@NotNull Long id,
		String titulo,
		String mensaje,
		DatosActualizarUsuario autor,
		DatosActualizarCurso curso
		) {

}
