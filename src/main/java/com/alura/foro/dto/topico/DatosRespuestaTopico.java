package com.alura.foro.dto.topico;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;

public record DatosRespuestaTopico(Long id,String titulo) {

	public DatosRespuestaTopico(Topico topico) {
		this(topico.getId(),topico.getTitulo());
	}

}
