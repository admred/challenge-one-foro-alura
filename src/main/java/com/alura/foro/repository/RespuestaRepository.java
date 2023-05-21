package com.alura.foro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.modelo.Respuesta;
import com.alura.foro.modelo.Topico;

public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {

	Page<Respuesta> findAll(Pageable paginacion);
}
