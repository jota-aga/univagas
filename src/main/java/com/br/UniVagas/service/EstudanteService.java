package com.br.UniVagas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.entity.Candidatura;
import com.br.UniVagas.entity.Estudante;
import com.br.UniVagas.repository.CandidaturaRepository;
import com.br.UniVagas.repository.EstudanteRepository;

@Service
public class EstudanteService {
	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	public List<Estudante> findAll() {
		List<Estudante> estudantes = estudanteRepository.findAll();
		
		return estudantes;
	}
	
	public void salvar(Estudante estudante) {
		Optional<Estudante> estudanteRepetido = estudanteRepository.findByCpf(estudante.getCpf());
		
		if(estudanteRepetido.isPresent()) {
			if(estudante.getId() == null) {
				return;
			}
			else if(estudante.getId() != estudanteRepetido.get().getId()) {
				return;
			}
		}
		
		estudanteRepository.save(estudante);
	}
	
	public void excluir(Estudante estudante) {
		List<Candidatura> candidaturas = candidaturaRepository.findAllByEstudanteId(estudante.getId());
		
		if(candidaturas != null && !candidaturas.isEmpty()) {
			candidaturas.forEach(candidatura -> candidatura.setEstudante(null));
		}
		
		candidaturaRepository.saveAll(candidaturas);
		
		estudanteRepository.delete(estudante);
	}
}
