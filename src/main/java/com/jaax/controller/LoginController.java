package com.jaax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaax.request.AuthResponse;
import com.jaax.request.AuthenticationRequest;
import com.jaax.request.RegisterRequest;
import com.jaax.service.AuthService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class LoginController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping(value = "/authenticate", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AuthResponse> authenticate (@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
