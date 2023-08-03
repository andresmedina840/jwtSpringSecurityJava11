package com.jaax.service;

import com.jaax.request.AuthResponse;
import com.jaax.request.AuthenticationRequest;
import com.jaax.request.RegisterRequest;

public interface AuthService {
	
	AuthResponse register (RegisterRequest requuest);
	
	AuthResponse authenticate (AuthenticationRequest requuest);

}
