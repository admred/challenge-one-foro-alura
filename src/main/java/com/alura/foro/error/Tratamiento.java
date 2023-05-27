package com.alura.foro.error;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RestControllerAdvice
public class Tratamiento {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratarError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratarError400(MethodArgumentNotValidException e) {
		var  errores=e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
		return ResponseEntity.badRequest().body(errores);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> tratarUsuarioNoEncontrado(UsernameNotFoundException e){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("*PLOP* Usuario no encontrado.\n");
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> tratarNotAutorizado(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("BEEP! Access Denied.\n");
	}
	
	private record DatosErrorValidacion(String campo,String error) {
		public DatosErrorValidacion(FieldError error) {
			this(error.getField(),error.getDefaultMessage());
		}
	}
}
