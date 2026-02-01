package com.br.UniVagas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.HabilidadeDTO;
import com.br.UniVagas.entity.Habilidade;
import com.br.UniVagas.exception.AlreadyExistsException;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.HabilidadeMapper;
import com.br.UniVagas.repository.HabilidadeRepository;

@Service
public class HabilidadeService {
	@Autowired
	private HabilidadeRepository habilidadeRepository;
	
	public void saveHabilidade(Habilidade habilidade) {
		validarNomeRepetido(habilidade);
		habilidadeRepository.save(habilidade);
	}
	
	public void deleteHabilidade(Habilidade habilidade) {
		habilidadeRepository.delete(habilidade);
	}
	
	public Habilidade findHabilidadeById(Integer id) {
		Optional<Habilidade> optionalHabilidade = habilidadeRepository.findById(id);
		
		return optionalHabilidade.orElseThrow(() -> new IdNotFoundException());
	}
	
	public void update(Integer id, HabilidadeDTO habilidadeDTO) {
		Habilidade habilidade = findHabilidadeById(id);
		
		habilidade = HabilidadeMapper.update(habilidade, habilidadeDTO);
		
		saveHabilidade(habilidade);
	}
	
	public List<Habilidade> findAllHabilidade() {		
		return habilidadeRepository.findAll();
	}
	
	private void validarNomeRepetido(Habilidade habilidade) {
		Optional<Habilidade> optionalHabilidadeRepetida = habilidadeRepository.findByNomeIgnoreCase(habilidade.getNome());
		
		if(optionalHabilidadeRepetida.isPresent()) {
			if(habilidade.getId() == null) {
				throw new AlreadyExistsException("Habilidade");
			}
			
			else {
				Habilidade habilidadeRepetida = optionalHabilidadeRepetida.get();
				
				if(habilidade.getId() != habilidadeRepetida.getId()) {
					System.out.println(habilidade.getId() +"-"+ habilidadeRepetida.getId());
					throw new AlreadyExistsException("Habilidade");
				}
			}
		}
	}

	
}
