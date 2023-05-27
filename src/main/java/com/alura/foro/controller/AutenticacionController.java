package com.alura.foro.controller;

import com.alura.foro.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import com.alura.foro.security.DatosJWTToken;
import com.alura.foro.security.TokenService;
import com.alura.foro.dto.usuario.DatosAutenticacionUsuarios;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> autenticacionUsuario(@RequestBody @Valid DatosAutenticacionUsuarios datosAutenticacionUsuarios) {
		Authentication authToken=new UsernamePasswordAuthenticationToken(
				datosAutenticacionUsuarios.login(),
				datosAutenticacionUsuarios.contrasena());
	
		var usuarioAutenticado = authenticationManager.authenticate(authToken);	
		var JWTtoken=tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
		return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
	}
}
