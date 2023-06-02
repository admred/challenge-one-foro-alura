package com.alura.foro.controller;

import com.alura.foro.modelo.Usuario;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.alura.foro.security.DatosJWTToken;
import com.alura.foro.security.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
	
	
	@Operation(summary = "Autenticacion", description = "Genera un token JWT para poder usar el DELETE")
	@ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType="application/json",schema=@Schema(implementation=DatosJWTToken.class))})
	@ApiResponse(responseCode = "403", description = "Forbidden",content=@Content)
	@PostMapping
	public ResponseEntity<?> autenticacionUsuario(@ParameterObject @Valid @RequestBody DatosAutenticacionUsuarios datosAutenticacionUsuarios) {
		
		Authentication authToken=new UsernamePasswordAuthenticationToken(
				datosAutenticacionUsuarios.login(),
				datosAutenticacionUsuarios.contrasena());
	
		var usuarioAutenticado = authenticationManager.authenticate(authToken);	
		var JWTtoken=tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
		return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
	}
}
