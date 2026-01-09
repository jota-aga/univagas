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

import com.br.UniVagas.dto.CandidaturaDTO;
import com.br.UniVagas.entity.Candidatura;
import com.br.UniVagas.service.CandidaturaService;

@RestController
@RequestMapping("/candidatura")
public class CandidaturaController {
	
	@Autowired
	private CandidaturaService candidaturaService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<?> findAllCandidatura(){
		List<Candidatura> candidaturas = candidaturaService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(candidaturas);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_ESTUDANTE')")
	public ResponseEntity<?> createCandidatura(@RequestBody CandidaturaDTO candidaturaDTO, JwtAuthenticationToken token){
		candidaturaService.create(candidaturaDTO, token);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA)")
	public ResponseEntity<?> updateCandidatura(@PathVariable Integer id, @RequestBody CandidaturaDTO candidaturaDTO, JwtAuthenticationToken token) throws Exception{
		candidaturaService.update(id, candidaturaDTO, token);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> deleteCandidatura(@PathVariable Integer id, JwtAuthenticationToken token) throws Exception{
		candidaturaService.delete(id, token);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/vaga/{id}")
	public ResponseEntity<?> findCandidaturaByVagaId(@PathVariable Integer vagaId, JwtAuthenticationToken token){
		List<Candidatura> candidaturas = candidaturaService.findByVagaId(vagaId, token);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(candidaturas);
	}
}
