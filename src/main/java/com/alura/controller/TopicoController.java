package com.alura.controller;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import com.alura.dto.topico.DatosRegistroTopico;
import com.alura.dto.topico.DatosRepuestaTopico;
import com.alura.modelo.Topico;
import com.alura.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@PostMapping
	public ResponseEntity registroTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,UriComponentsBuilder uriComponentBuilder ) {
		Topico topico=  topicoRepository.save(new Topico(datosRegistroTopico));
		DatosRepuestaTopico datosRespuestaTopico=new DatosRepuestaTopico(topico);
		URI uri=uriComponentBuilder.path("/topicos/{id}").buildAndExpand(topico.getId() ).toUri();
		return ResponseEntity.created(uri).body(datosRespuestaTopico);
	}
	/*
	@GetMapping("/{id}")
	public ResponseEntity detalleTopico(@PathVaraible Long id) {
		Topico topico=topicoRepository.getReferenceById(id);
		DatosRepuestaTopico datosTopico=new DatosRepuestaTopico();
		return ResponseEntity.ok(datosRepuestaTopico);
	}
*/
}