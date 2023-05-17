package com.alura.foro.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	
	@GetMapping
	public String detailTest(@RequestBody String content) {
		return "\nDETAIL : "+content+"\n";
	}
	
	@PostMapping
	public String createTest(@RequestBody String content) {
		return "\nCREADO  : "+content+"\n";
	}
	
	@PutMapping 
	public String updateTest(@RequestBody String content) {
		return "\nUPDATE : "+content+"\n";
	}
	
	@DeleteMapping
	public String deleteTest(@RequestBody String content) {
		return "\nDELETE : "+content+"\n";
	}
}
