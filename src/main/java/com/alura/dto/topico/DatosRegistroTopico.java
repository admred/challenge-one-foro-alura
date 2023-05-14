package com.alura.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.alura.dto.curso.DatosRegistroCurso;
import com.alura.dto.usuario.DatosRegistroUsuario;
import com.alura.modelo.Curso;
import com.alura.modelo.Usuario;

public record DatosRegistroTopico(
		
		@NotBlank
		String titulo,
		
		@NotBlank
		String mensaje,
		
		@NotNull
		DatosRegistroUsuario autor,
		
		@NotNull
		DatosRegistroCurso curso) {

}
