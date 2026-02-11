package com.br.UniVagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> findAllApplication(){
		List<Application> applications = applicationService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(applications);
	}
	
	@PostMapping
	public ResponseEntity<?> createApplication(@RequestBody ApplicationDTO applicationDTO){
		applicationService.create(applicationDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateApplication(@PathVariable Integer id, @RequestBody ApplicationDTO applicationDTO) throws Exception{
		applicationService.update(id, applicationDTO);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteApplication(@PathVariable Integer id) throws Exception{
		applicationService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/job/{id}")
	public ResponseEntity<?> findApplicationByJobId(@PathVariable Integer jobId){
		List<Application> applications = applicationService.findByJobId(jobId);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(applications);
	}
}
