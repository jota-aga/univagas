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

import com.br.UniVagas.dto.CompanyDTO;
import com.br.UniVagas.entity.Company;
import com.br.UniVagas.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public List<Company> findAllCompanys(){
		return companyService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyDTO companyDTO) {		
		companyService.create(companyDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCompany(@PathVariable Integer id, @Valid @RequestBody CompanyDTO companyRequest){
		companyService.update(id, companyRequest);
		
		return ResponseEntity.status(HttpStatus.OK).body(null); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable Integer id) throws Exception{
		companyService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(null); 
	}
	
	@GetMapping("/search-razaosocial")
	public ResponseEntity<?> findAllByRazaoSocial(@RequestParam String legalName){
		List<Company> companys = companyService.findAllByLegalName(legalName);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(companys);
	}
}
