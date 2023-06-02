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

import com.alura.foro.dto.respuesta.DatosRespuestaRespuesta;
import com.alura.foro.dto.respuesta.DatosActualizarRespuesta;
import com.alura.foro.dto.respuesta.DatosRegistroRespuesta;
import com.alura.foro.repository.RespuestaRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Hidden;

import com.alura.foro.modelo.Respuesta;
import com.alura.foro.modelo.StatusTopico;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Hidden
@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
	
	@Autowired
	private RespuestaRepository respuestaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaRespuesta> registroRespuesta(
			@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
			UriComponentsBuilder uriComponentBuilder
			) {
		
		Usuario autor=usuarioRepository.getReferenceById(datosRegistroRespuesta.autor_id());
		Topico topico=topicoRepository.getReferenceById(datosRegistroRespuesta.topico_id());
		topico.setStatus(StatusTopico.NO_SOLUCIONADO);
		Respuesta respuesta=new Respuesta(
				datosRegistroRespuesta.mensaje(),
				autor,
				topico);
		respuestaRepository.save(respuesta);
		DatosRespuestaRespuesta datosRespuestaRespuesta=new DatosRespuestaRespuesta(respuesta);
				
		URI uri=uriComponentBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId() ).toUri();
		return ResponseEntity.created(uri).body(datosRespuestaRespuesta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaRespuesta> detalleRespuesta(@PathVariable Long id) {
		Respuesta respuesta=respuestaRepository.getReferenceById(id);
		DatosRespuestaRespuesta datosDetalleRespuesta=new DatosRespuestaRespuesta(respuesta);
		return ResponseEntity.ok(datosDetalleRespuesta);
	}
	
	@GetMapping
	public Page<DatosRespuestaRespuesta> listadoRespuestas(Pageable paginacion){
		return respuestaRepository.findAll(paginacion).map(DatosRespuestaRespuesta::new);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteRespuesta(@PathVariable Long id) {
		respuestaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DatosRespuestaRespuesta> actualizarRespuesta(
			@PathVariable Long id,
			@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
		
		Respuesta respuesta=respuestaRepository.getReferenceById(id);
		respuesta.actualizarRespuesta(datosActualizarRespuesta);
		return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta));
	}
}
