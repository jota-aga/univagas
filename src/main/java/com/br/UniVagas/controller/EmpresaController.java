package com.br.UniVagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.UniVagas.dto.EmpresaDTO;
import com.br.UniVagas.entity.Empresa;
import com.br.UniVagas.mappers.EmpresaMapper;
import com.br.UniVagas.service.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	EmpresaService empresaService;
	
	@GetMapping
	public List<Empresa> findAllEmpresas(){
		return empresaService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> saveEmpresa(@Valid @RequestBody EmpresaDTO empresaRequest) {
		Empresa empresa = EmpresaMapper.toEntity(empresaRequest);
		
		empresaService.save(empresa);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmpresa(@PathVariable Integer id, @Valid @RequestBody EmpresaDTO empresaRequest){
		empresaService.update(id, empresaRequest);
		
		return ResponseEntity.status(HttpStatus.OK).body(null); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpresa(@PathVariable Integer id) throws Exception{
		empresaService.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(null); 
	}
	
	@GetMapping("/search-razaosocial")
	public ResponseEntity<?> findAllByRazaoSocial(@RequestParam String razaoSocial){
		List<Empresa> empresas = empresaService.findAllByRazaoSocial(razaoSocial);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(empresas);
	}
}
