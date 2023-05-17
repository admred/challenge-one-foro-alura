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

import com.alura.foro.dto.topico.DatosRespuestaTopico;
import com.alura.foro.dto.usuario.DatosRegistroUsuario;
import com.alura.foro.dto.usuario.DatosRespuestaUsuario;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;

import jakarta.validation.Valid;

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
	
	
}
