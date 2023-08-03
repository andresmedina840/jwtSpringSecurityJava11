package com.jaax.security;

import com.jaax.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	private final UsuarioRepository usuarioRepository;

	@Bean
	UserDetailsService userDetailsService() {
		return username -> usuarioRepository.findByUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}

	@Bean
	AuthenticationProvider uAuthenticationProvider() {
		DaoAuthenticationProvider uAuthenticationProvider = new DaoAuthenticationProvider();
		uAuthenticationProvider.setUserDetailsService(userDetailsService());
		;
		uAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return uAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
