package com.alura.foro.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.alura.foro.modelo.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String apiSecret;

	public String generarToken(Usuario usuario) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    return JWT.create()
		        .withIssuer("API Foro Alura")
		        .withSubject(usuario.getLogin())
		        .withClaim("id",usuario.getId())
		        .withExpiresAt(generarFechaDeExpiracion())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("NO SE PUDO GENERAR TOKEN");
		}
	}
	
	private Instant generarFechaDeExpiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}
	
	
	public String getSubject(String token) {
		if(token == null) return null;
		
		DecodedJWT verifier=null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    verifier = JWT.require(algorithm)
		        .withIssuer("API Foro Alura")
		        .build()
		    	.verify(token);
		     verifier.getSubject();
		} catch (JWTVerificationException exception){
		    return null;
		}
		
		if(verifier.getSubject() == null) {
			return null;
		}
		return 	verifier.getSubject();	
	}

}


