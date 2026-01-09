package com.br.UniVagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.UniVagas.dto.EstudanteDTO;
import com.br.UniVagas.entity.Estudante;
import com.br.UniVagas.service.EstudanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {
	
	@Autowired
	private EstudanteService estudanteService;
	
	@GetMapping
	public List<Estudante> findAllEstudante(){
		return estudanteService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> createEstudante(@Valid @RequestBody EstudanteDTO estudanteDTO){		
		estudanteService.create(estudanteDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_ESTUDANTE' or hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<?> updateEstudante(@PathVariable Integer id, @Valid @RequestBody EstudanteDTO estudanteDTO){		
		estudanteService.update(id, estudanteDTO);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_ESTUDANTE' or hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<?> deleteEstudante(@PathVariable Integer id){		
		estudanteService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/search-descricao")
	public ResponseEntity<?> findEstudanteByDescricao(@RequestParam String descricao){		
		List<Estudante> estudantes = estudanteService.findByDescricao(descricao);
		
		return ResponseEntity.status(HttpStatus.OK).body(estudantes);
	}
}
