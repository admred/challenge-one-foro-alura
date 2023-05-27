package com.alura.foro.dto.usuario;

import com.alura.foro.modelo.Usuario;

public record DatosRespuestaUsuario(Long id,String nombre) {
	public DatosRespuestaUsuario(Usuario usuario) {
		this(usuario.getId(),usuario.getLogin());
	}

}
