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

import com.alura.foro.dto.usuario.DatosActualizarUsuario;
import com.alura.foro.dto.usuario.DatosRegistroUsuario;
import com.alura.foro.dto.usuario.DatosRespuestaUsuario;
import com.alura.foro.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Hidden;

import com.alura.foro.modelo.Usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Hidden
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaUsuario> registroUsuario(
			@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
			UriComponentsBuilder uriComponentBuilder
			) {
		Usuario usuario=new Usuario(datosRegistroUsuario);
		usuarioRepository.save(usuario);
		DatosRespuestaUsuario datosRespuestaUsuario=new DatosRespuestaUsuario(usuario);
				
		URI uri=uriComponentBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId() ).toUri();
		return ResponseEntity.created(uri).body(datosRespuestaUsuario);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaUsuario> detalleUsuario(@PathVariable Long id) {
		Usuario usuario=usuarioRepository.getReferenceById(id);
		DatosRespuestaUsuario datosRespuestaUsuario=new DatosRespuestaUsuario(usuario);
		return ResponseEntity.ok(datosRespuestaUsuario);
	}
	
	@GetMapping
	public Page<DatosRespuestaUsuario> listadoUsuarios(Pageable paginacion){
		return usuarioRepository.findAll(paginacion).map(DatosRespuestaUsuario::new);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(
			@PathVariable Long id,
			@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
		
		Usuario usuario=usuarioRepository.getReferenceById(id);
		usuario.actualizarUsuario(datosActualizarUsuario);
		return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
	}
}
