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

import com.br.UniVagas.dto.HabilidadeDTO;
import com.br.UniVagas.entity.Habilidade;
import com.br.UniVagas.mappers.HabilidadeMapper;
import com.br.UniVagas.service.HabilidadeService;

@RestController
@RequestMapping("/habilidade")
public class HabilidadeController {
	@Autowired
	private HabilidadeService habilidadeService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Habilidade> habilidades = habilidadeService.findAllHabilidade();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(habilidades);
	}
	
	@PostMapping
	public ResponseEntity<?> createHabilidade(@RequestBody HabilidadeDTO habilidadeDTO) {
		Habilidade habilidade = HabilidadeMapper.toEntity(habilidadeDTO);
		
		habilidadeService.saveHabilidade(habilidade);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateHabilidade(@PathVariable Integer id, @RequestBody HabilidadeDTO habilidadeDTO) {
		
		habilidadeService.update(id, habilidadeDTO);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
