package com.alura.foro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Topico;

public interface CursoRepository extends JpaRepository<Curso, Long> {
	Page<Curso> findAll(Pageable paginacion);
}
