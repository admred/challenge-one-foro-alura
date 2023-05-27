package com.alura.foro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.alura.foro.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Page<Usuario> findAll(Pageable paginacion);
	
	public UserDetails findByLogin(String nombreUsuario);
	
	
}
