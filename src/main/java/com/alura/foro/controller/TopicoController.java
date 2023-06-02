package com.alura.foro.controller;
import java.net.URI;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.dto.topico.DatosActualizarTopico;
import com.alura.foro.dto.topico.DatosDetalleTopico;
import com.alura.foro.dto.topico.DatosListadoTopico;
import com.alura.foro.dto.topico.DatosRegistroTopico;
import com.alura.foro.dto.topico.DatosRespuestaTopico;
import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.security.DatosJWTToken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private CursoRepository cursoRepository;
	
	
	@Operation(summary = "Crear", description = "Crea un topico.")
	@ApiResponse(responseCode = "204", description = "Content Created" ,content={@Content(mediaType="application/json",schema=@Schema(implementation=DatosRespuestaTopico.class))})  
	@PostMapping
	public ResponseEntity<DatosRespuestaTopico> registroTopico(
			@ParameterObject @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,		
			UriComponentsBuilder uriComponentBuilder ) {
		
		Usuario autor=((Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Curso curso=cursoRepository.getReferenceById( datosRegistroTopico.curso_id());

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
	
	@Operation(summary = "Detalle", description = "Obtener el detalle de un topico.")
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleTopico> detalleTopico(@PathVariable Long id) {
		Topico topico=topicoRepository.getReferenceById(id);
		DatosDetalleTopico datosDetalleTopico=new DatosDetalleTopico(topico);
		return ResponseEntity.ok(datosDetalleTopico);
	}
	
	@Operation(summary = "Listado", description = "Obtener una lista de topicos.")
	@Parameter(in=ParameterIn.QUERY,name="size",example="10")
	@Parameter(in=ParameterIn.QUERY,name="sort", example="titulo")
	@Parameter(in=ParameterIn.QUERY,name="offset")
	@ApiResponse(responseCode = "200", description = "OK",content=@Content)
	@GetMapping
	public Page<DatosListadoTopico> listadoTopicos(@Parameter(hidden=true)  @ParameterObject  Pageable paginacion){
		return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
	}
		
	@Operation(summary = "Actualizar", description = "Actualiza un topico.")
	@ApiResponse(responseCode = "200", description = "OK",content=@Content)
	@ApiResponse(responseCode = "404", description = "Not Found",content=@Content)
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
			@PathVariable Long id,
			@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
		
		Topico topico=topicoRepository.getReferenceById(id);
		topico.actualizarTopico(datosActualizarTopico);
		return ResponseEntity.ok(new DatosRespuestaTopico(topico));
	}
	
	@SecurityRequirement(name = "bearer-key")
	@Parameter(in=ParameterIn.HEADER,name = "Authorization", description = "Necesita el JWT Token para poder usar DELETE" ,required=true)
	@Operation(summary = "Eliminar", description = "Elimina un topicos.")
	@ApiResponse(responseCode = "200", description = "OK",content=@Content)
	@ApiResponse(responseCode = "403", description = "Forbidden",content=@Content)
	@ApiResponse(responseCode = "404", description = "Not Found",content=@Content)
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
		topicoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}	
	
}