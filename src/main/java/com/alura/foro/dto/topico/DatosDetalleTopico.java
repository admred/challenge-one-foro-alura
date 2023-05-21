package com.alura.foro.dto.topico;

import java.time.LocalDateTime;

import com.alura.foro.modelo.StatusTopico;
import com.alura.foro.modelo.Topico;

public record DatosDetalleTopico(Long id,String titulo,String mensaje,LocalDateTime fechaCreacion,StatusTopico status) {

	public DatosDetalleTopico(Topico topico) {
		this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus() );
	}

}
