package com.br.UniVagas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.UniVagas.dto.LoginDTO;
import com.br.UniVagas.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDTO){
		String token = userService.doLogin(loginDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(token);
	}
}
