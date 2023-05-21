package com.alura.foro.dto.topico;

import java.time.LocalDateTime;

import com.alura.foro.modelo.StatusTopico;
import com.alura.foro.modelo.Topico;

public record DatosListadoTopico(Long id,String titulo,LocalDateTime fechaCreacion,StatusTopico status) {
	
	public DatosListadoTopico(Topico topico) {
		this(topico.getId(),topico.getTitulo(),topico.getFechaCreacion(),topico.getStatus());
	}

}
