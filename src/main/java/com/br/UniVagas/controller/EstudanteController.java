package com.br.UniVagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.UniVagas.entity.Estudante;
import com.br.UniVagas.service.EstudanteService;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {
	
	@Autowired
	private EstudanteService estudanteService;
	
	public List<Estudante> findAllEstudante(){
		return estudanteService.findAll();
	}
}
