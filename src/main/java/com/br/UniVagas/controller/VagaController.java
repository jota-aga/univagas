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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.UniVagas.dto.VagaDTO;
import com.br.UniVagas.entity.Vaga;
import com.br.UniVagas.service.VagaService;

@RestController
@RequestMapping("/vaga")
public class VagaController {
	
	@Autowired
	private VagaService vagaService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> createVaga(@RequestBody VagaDTO vagaDTO, JwtAuthenticationToken token){
		vagaService.create(vagaDTO, token);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> updateVaga(@PathVariable Integer id, @RequestBody VagaDTO vagaDTO,  JwtAuthenticationToken token){
		vagaService.update(vagaDTO, id, token);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> deleteVaga(@PathVariable Integer id, JwtAuthenticationToken token){
		
		vagaService.delete(id, token);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<?> findAllVaga(){
		return ResponseEntity.status(HttpStatus.OK).body(vagaService.findAll());
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findVagaByDescricaoOrTitulo(@RequestParam String termoDePesquisa){
		List<Vaga> vagas = vagaService.findByTermoDePesquisa(termoDePesquisa);
		
		return ResponseEntity.status(HttpStatus.OK).body(vagas);
	}
}
