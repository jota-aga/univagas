package com.br.UniVagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.UniVagas.dto.ApplicationDTO;
import com.br.UniVagas.entity.Application;
import com.br.UniVagas.service.ApplicationService;

@RestController
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<?> findAllApplication(){
		List<Application> applications = applicationService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(applications);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_ESTUDANTE')")
	public ResponseEntity<?> createApplication(@RequestBody ApplicationDTO applicationDTO, JwtAuthenticationToken token){
		applicationService.create(applicationDTO, token);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> updateApplication(@PathVariable Integer id, @RequestBody ApplicationDTO applicationDTO, JwtAuthenticationToken token) throws Exception{
		applicationService.update(id, applicationDTO, token);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> deleteApplication(@PathVariable Integer id, JwtAuthenticationToken token) throws Exception{
		applicationService.delete(id, token);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/vaga/{id}")
	public ResponseEntity<?> findApplicationByVagaId(@PathVariable Integer vagaId, JwtAuthenticationToken token){
		List<Application> applications = applicationService.findByVagaId(vagaId, token);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(applications);
	}
}
