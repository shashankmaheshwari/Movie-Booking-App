package com.moviebookingapp.controller;

import com.moviebookingapp.entities.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.entities.AuthRequest;
import com.moviebookingapp.service.impl.JwtService;

@RestController
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private JwtService jwtService;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@PostMapping("/login")
	public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUserName());
		} else {
			throw new UsernameNotFoundException("invalid user request !"); 
		} 

	}
}
