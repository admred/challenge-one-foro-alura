package com.alura.foro.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.dto.curso.DatosRegistroCurso;
import com.alura.foro.dto.curso.DatosRespuestaCurso;
import com.alura.foro.modelo.Curso;
import com.alura.foro.repository.CursoRepository;

import jakarta.validation.Valid;


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
		
	
}
