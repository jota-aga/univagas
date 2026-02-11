package com.br.UniVagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.UniVagas.dto.CompetenceDTO;
import com.br.UniVagas.entity.Competence;
import com.br.UniVagas.mappers.CompetenceMapper;
import com.br.UniVagas.service.CompetenceService;

@RestController
@RequestMapping("/competence")
public class CompetenceController {
	@Autowired
	private CompetenceService competenceService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Competence> competences = competenceService.findAllCompetence();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(competences);
	}
	
	@PostMapping
	public ResponseEntity<?> createCompetence(@RequestBody CompetenceDTO competenceDTO) {
		Competence competence = CompetenceMapper.toEntity(competenceDTO);
		
		competenceService.saveCompetence(competence);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCompetence(@PathVariable Integer id, @RequestBody CompetenceDTO competenceDTO) {
		
		competenceService.update(id, competenceDTO);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
