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

import com.br.UniVagas.dto.JobDTO;
import com.br.UniVagas.entity.Job;
import com.br.UniVagas.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<?> createJob(@RequestBody JobDTO jobDTO){
		jobService.create(jobDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateJob(@PathVariable Integer id, @RequestBody JobDTO jobDTO){
		jobService.update(jobDTO, id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping
	public ResponseEntity<?> findAllJob(){
		return ResponseEntity.status(HttpStatus.OK).body(jobService.findAll());
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findJobByDescricaoOrTitulo(@RequestParam String termoDePesquisa){
		List<Job> jobs = jobService.findByTermoDePesquisa(termoDePesquisa);
		
		return ResponseEntity.status(HttpStatus.OK).body(jobs);
	}
	
	@GetMapping("/company/{id}")
	public ResponseEntity<?> findAllJobByCompany(@PathVariable Integer id){
		List<Job> jobs = jobService.findAllByCompany(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(jobs);
	}
}
