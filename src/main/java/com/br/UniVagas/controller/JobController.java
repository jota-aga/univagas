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
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> createJob(@RequestBody JobDTO jobDTO, JwtAuthenticationToken token){
		jobService.create(jobDTO, token);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> updateJob(@PathVariable Integer id, @RequestBody JobDTO jobDTO,  JwtAuthenticationToken token){
		jobService.update(jobDTO, id, token);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<?> findAllJob(){
		return ResponseEntity.status(HttpStatus.OK).body(jobService.findAll());
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findJobByDescricaoOrTitulo(@RequestParam String termoDePesquisa){
		List<Job> jobs = jobService.findByTermoDePesquisa(termoDePesquisa);
		
		return ResponseEntity.status(HttpStatus.OK).body(jobs);
	}
	
	@GetMapping("/company")
	@PreAuthorize("hasAuthority('SCOPE_EMPRESA')")
	public ResponseEntity<?> findAllJobByCompany(JwtAuthenticationToken token){
		List<Job> jobs = jobService.findAllByCompany(token);
		
		return ResponseEntity.status(HttpStatus.OK).body(jobs);
	}
}
