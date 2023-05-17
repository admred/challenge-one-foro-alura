package com.alura.foro.dto.usuario;

import com.alura.foro.modelo.Usuario;

public record DatosRespuestaUsuario(Long id,String nombre,String email) {
	public DatosRespuestaUsuario(Usuario usuario) {
		this(usuario.getId(),usuario.getNombre(),usuario.getEmail());
	}

}
