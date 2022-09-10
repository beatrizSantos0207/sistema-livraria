package com.livraria.livraria.controller;

import com.livraria.livraria.security.model.dto.LoginDTO;
import com.livraria.livraria.security.model.dto.UserDTO;
import com.livraria.livraria.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

@RestController
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> login(@Valid @RequestBody UserDTO userDTO) throws LoginException {
		return ResponseEntity.ok(authService.logar(userDTO));
	}
	
}