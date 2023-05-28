package com.alura.foro.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.alura.foro.repository.UsuarioRepository;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class SecurityFilter extends OncePerRequestFilter  {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		String jwtToken = extractToken(request);
		if (jwtToken == null) {
			filterChain.doFilter(request, response);
			return;
		}
        
        String login=tokenService.getSubject(jwtToken);
        if (login == null) {
        	filterChain.doFilter(request, response);
        	return;
        }
        
        UserDetails userDetails = usuarioRepository.findByLogin(login);
        if (userDetails == null) { 
        	filterChain.doFilter(request, response);
        	return;
        }
        
        
        var authentication = new UsernamePasswordAuthenticationToken(
        		userDetails, 
        		null,
                userDetails.getAuthorities()); 
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
               
        filterChain.doFilter(request, response);	
	}
	
	private String extractToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");		
		if(authHeader == null) return null;
		
		String jwtToken=authHeader.replace("Bearer ","");
		if(jwtToken == null || jwtToken.isEmpty()) return null;
		
		return jwtToken;
	}
}