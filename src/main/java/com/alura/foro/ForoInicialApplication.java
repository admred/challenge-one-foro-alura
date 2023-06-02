package com.alura.foro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class ForoInicialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoInicialApplication.class, args);
	}
}
