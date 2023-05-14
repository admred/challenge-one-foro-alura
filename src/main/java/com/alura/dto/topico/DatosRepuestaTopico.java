package com.alura.dto.topico;

import com.alura.modelo.Curso;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;

public record DatosRepuestaTopico(Long id,String titulo) {

	public DatosRepuestaTopico(Topico topico) {
		this(topico.getId(),topico.getTitulo());
	}

}
