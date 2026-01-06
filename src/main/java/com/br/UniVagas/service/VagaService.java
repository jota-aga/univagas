package com.br.UniVagas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.entity.Vaga;
import com.br.UniVagas.repository.VagaRepository;

@Service
public class VagaService {
	
	@Autowired
	private VagaRepository vagaRepository;
	
	public void salvarVaga(Vaga vaga) {
		vagaRepository.save(vaga);
	}
	
	public void excluirVaga(Vaga vaga) {
		vagaRepository.delete(vaga);
	}
}
