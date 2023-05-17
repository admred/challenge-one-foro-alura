package com.alura.foro.controller;
import java.net.URI;
import java.util.List;

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

import com.alura.foro.dto.topico.DatosRegistroTopico;
import com.alura.foro.dto.topico.DatosRespuestaTopico;
import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private CursoRepository cursoRepository;
	
	
	@PostMapping
	public ResponseEntity<DatosRespuestaTopico> registroTopico(
			@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,		
			UriComponentsBuilder uriComponentBuilder ) {
		/* NOTA: el autor deberia o podria obtenerse desde la sesion iniciada */
		Usuario autor=usuarioRepository.getReferenceById(datosRegistroTopico.autor().id());
		Curso curso=cursoRepository.getReferenceById( datosRegistroTopico.curso().id());

		Topico topico=new Topico(
				datosRegistroTopico.titulo(),
				datosRegistroTopico.mensaje(),
				autor,
				curso
				);				
				
		topicoRepository.save(topico);
		
		DatosRespuestaTopico datosRespuestaTopico=new DatosRespuestaTopico(topico);
		URI uri=uriComponentBuilder.path("/topicos/{id}").buildAndExpand(topico.getId() ).toUri();
		return ResponseEntity.created(uri).body(datosRespuestaTopico);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaTopico> detalleTopico(@PathVariable Long id) {
		Topico topico=topicoRepository.getReferenceById(id);
		DatosRespuestaTopico datosRespuestaTopico=new DatosRespuestaTopico(topico);
		return ResponseEntity.ok(datosRespuestaTopico);
	}
	
	@GetMapping
	public List<Topico> listarTopics(){
		/* TODO: en construccion */
		return topicoRepository.findAll();
		
	}
	
}