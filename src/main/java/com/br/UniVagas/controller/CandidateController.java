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

import com.br.UniVagas.dto.CandidateDTO;
import com.br.UniVagas.entity.Candidate;
import com.br.UniVagas.service.CandidateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@GetMapping
	public List<Candidate> findAllCandidate(){
		return candidateService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> createCandidate(@Valid @RequestBody CandidateDTO candidateDTO){		
		candidateService.create(candidateDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCandidate(@PathVariable Integer id, @Valid @RequestBody CandidateDTO candidateDTO){		
		candidateService.update(id, candidateDTO);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable Integer id){		
		candidateService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/search-descricao")
	public ResponseEntity<?> findCandidateByDescricao(@RequestParam String descricao){		
		List<Candidate> candidates = candidateService.findByDescricao(descricao);
		
		return ResponseEntity.status(HttpStatus.OK).body(candidates);
	}
}
