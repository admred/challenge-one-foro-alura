package com.alura.foro.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
	
	Page<Topico> findAll(Pageable paginacion);

}
