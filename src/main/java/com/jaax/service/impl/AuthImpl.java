package com.jaax.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jaax.entity.Role;
import com.jaax.entity.Usuario;
import com.jaax.repository.UsuarioRepository;
import com.jaax.request.AuthResponse;
import com.jaax.request.AuthenticationRequest;
import com.jaax.request.RegisterRequest;
import com.jaax.security.JwtService;
import com.jaax.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthImpl implements AuthService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	public AuthResponse register(RegisterRequest request) {
		
		System.out.println("Usuario: " + request.getUsuario());
		
		var user = Usuario.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.usuario(request.getUsuario()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
				.build();

		usuarioRepository.save(user);
		String jwtToken = jwtService.generarJsonWebToken(user);

		return AuthResponse.builder().token(jwtToken).build();
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getPassword()));

		var user = usuarioRepository.findByUsuario(request.getUsuario()).orElseThrow();
		var jwtToken = jwtService.generarJsonWebToken(user);
		
		return AuthResponse.builder().token(jwtToken).build();
	}

}
