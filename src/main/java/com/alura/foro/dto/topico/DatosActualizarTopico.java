package com.alura.foro.dto.topico;

import com.alura.foro.dto.curso.DatosActualizarCurso;
import com.alura.foro.dto.usuario.DatosActualizarUsuario;
import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
		
		String titulo,
		
		String mensaje
		
		) {

}
