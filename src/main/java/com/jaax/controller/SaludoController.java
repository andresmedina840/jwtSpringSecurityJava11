package com.jaax.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/api/saludo")
public class SaludoController {
	
	@GetMapping(value = "/sayHello", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String sayHello()
	{
		return "Hello from API JAAX";
	}
	
	@GetMapping(value = "/sayHelloProtected", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String sayHelloProtected()
	{
		return "Hello from API JAAX Protected";
	}
}
