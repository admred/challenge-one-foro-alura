package com.alura.foro.dto.curso;

import com.alura.foro.modelo.Curso;

public record DatosRespuestaCurso(Long id,String nombre,String categoria) {
	public DatosRespuestaCurso(Curso curso) {
		this(curso.getId(),curso.getNombre(),curso.getCategoria());
	}

}
