package com.alura.foro.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.dto.curso.DatosActualizarCurso;
import com.alura.foro.dto.curso.DatosRegistroCurso;
import com.alura.foro.dto.curso.DatosRespuestaCurso;
import com.alura.foro.dto.topico.DatosActualizarTopico;
import com.alura.foro.dto.topico.DatosRespuestaTopico;
import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Topico;
import com.alura.foro.repository.CursoRepository;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Hidden
@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaCurso> registroCurso(
			@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
			UriComponentsBuilder uriComponentBuilder
			) {
		Curso Curso=new Curso(datosRegistroCurso);
		cursoRepository.save(Curso);
		DatosRespuestaCurso datosRespuestaCurso=new DatosRespuestaCurso(Curso);
				
		URI uri=uriComponentBuilder.path("/Cursos/{id}").buildAndExpand(Curso.getId() ).toUri();
		return ResponseEntity.created(uri).body(datosRespuestaCurso);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaCurso> detalleCurso(@PathVariable Long id) {
		Curso Curso=cursoRepository.getReferenceById(id);
		DatosRespuestaCurso datosRespuestaCurso=new DatosRespuestaCurso(Curso);
		return ResponseEntity.ok(datosRespuestaCurso);
	}
		
	@GetMapping
	public Page<DatosRespuestaCurso> listadoCursos(@PageableDefault(sort = "nombre",size=10 ) Pageable paginacion){
		
		return cursoRepository.findAll(paginacion).map(DatosRespuestaCurso::new);
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
		cursoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DatosRespuestaCurso> actualizarCurso(
			@PathVariable Long id,
			@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
		
		Curso curso=cursoRepository.getReferenceById(id);
		curso.actualizarCurso(datosActualizarCurso);
		return ResponseEntity.ok(new DatosRespuestaCurso(curso));
	}
	
}
