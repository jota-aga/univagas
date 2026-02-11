package com.br.UniVagas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.CompetenceDTO;
import com.br.UniVagas.entity.Competence;
import com.br.UniVagas.exception.AlreadyExistsException;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.CompetenceMapper;
import com.br.UniVagas.repository.CompetenceRepository;

@Service
public class CompetenceService {
	@Autowired
	private CompetenceRepository competenceRepository;
	
	public void saveCompetence(Competence competence) {
		validarNomeRepetido(competence);
		competenceRepository.save(competence);
	}
	
	public void deleteCompetence(Competence competence) {
		competenceRepository.delete(competence);
	}
	
	public Competence findCompetenceById(Integer id) {
		Optional<Competence> optionalCompetence = competenceRepository.findById(id);
		
		return optionalCompetence.orElseThrow(() -> new IdNotFoundException());
	}
	
	public void update(Integer id, CompetenceDTO competenceDTO) {
		Competence competence = findCompetenceById(id);
		
		competence = CompetenceMapper.update(competence, competenceDTO);
		
		saveCompetence(competence);
	}
	
	public List<Competence> findAllCompetence() {		
		return competenceRepository.findAll();
	}
	
	private void validarNomeRepetido(Competence competence) {
		Optional<Competence> optionalCompetenceRepetida = competenceRepository.findByNomeIgnoreCase(competence.getName());
		
		if(optionalCompetenceRepetida.isPresent()) {
			if(competence.getId() == null) {
				throw new AlreadyExistsException("Competence");
			}
			
			else {
				Competence competenceRepetida = optionalCompetenceRepetida.get();
				
				if(competence.getId() != competenceRepetida.getId()) {
					System.out.println(competence.getId() +"-"+ competenceRepetida.getId());
					throw new AlreadyExistsException("Competence");
				}
			}
		}
	}

	
}
