package com.alura.foro.dto.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

import com.alura.foro.dto.curso.DatosActualizarCurso;
import com.alura.foro.dto.curso.DatosRegistroCurso;
import com.alura.foro.dto.usuario.DatosActualizarUsuario;
import com.alura.foro.dto.usuario.DatosRegistroUsuario;
import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.StatusTopico;
import com.alura.foro.modelo.Usuario;

public record DatosRegistroTopico(
		
		@NotBlank
		String titulo,
		
		@NotBlank
		String mensaje,		
		
		@NotNull
		Long curso_id
		
		) {

}
