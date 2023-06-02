package com.alura.foro.security;

import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SpringDocConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		/* Todas las operaciones son publicas excepto para el DELETE */
		return  httpSecurity
					.csrf().disable()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		            .and().authorizeHttpRequests()
		            .requestMatchers("/login").permitAll()
		            .requestMatchers(HttpMethod.DELETE).authenticated()
		            .anyRequest().permitAll()
		            .and()
		            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
					.build();			
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
			  .info(new Info()
					  .title("Foro Alura")
                      .description("Foro ALura - Challenge ONE Back End - Sprint 02"))
	          .components(
	        		  new Components()
	        		  	.addSecuritySchemes("bearer-key",
	        		  			new SecurityScheme()
	        		  				.type(SecurityScheme.Type.HTTP)
	        		  				.scheme("bearer")
	        		  				.bearerFormat("JWT"))
	        		  	);
	}
	
}
