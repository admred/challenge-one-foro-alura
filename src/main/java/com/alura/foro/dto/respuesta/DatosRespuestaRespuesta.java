package com.alura.foro.dto.respuesta;

import com.alura.foro.modelo.Respuesta;

public record DatosRespuestaRespuesta(Long id,String mensaje) {
	public DatosRespuestaRespuesta(Respuesta respuesta) {
		this(respuesta.getId(),respuesta.getMensaje());
	}
}
