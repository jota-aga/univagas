package com.br.UniVagas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.CandidaturaDTO;
import com.br.UniVagas.entity.Candidatura;
import com.br.UniVagas.entity.Estudante;
import com.br.UniVagas.entity.Vaga;
import com.br.UniVagas.enums.StatusDaCandidatura;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.CandidaturaMapper;
import com.br.UniVagas.repository.CandidaturaRepository;
import com.br.UniVagas.repository.VagaRepository;

@Service
public class CandidaturaService {
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public void save(Candidatura candidatura) {
		candidaturaRepository.save(candidatura);
	}
	
	public void create(CandidaturaDTO candidaturaDTO, JwtAuthenticationToken token) {
		Candidatura candidatura = new Candidatura();
		
		Vaga vaga = vagaRepository.findById(candidaturaDTO.vagaId()).orElseThrow(() -> new IdNotFoundException());
		
		Estudante estudante = tokenService.findEstudanteByToken(token);
		
		candidatura.setVaga(vaga);
		candidatura.setEstudante(estudante);
		
		candidatura.setDataAplicacao(LocalDate.now());
		candidatura.setStatusDaCandidatura(StatusDaCandidatura.ENVIADA);
		
		save(candidatura);
	}
	
	public void update(Integer id, CandidaturaDTO candidaturaDTO, JwtAuthenticationToken token) {
		Candidatura candidatura = findById(id);
		
		tokenService.verifyEmpresaByToken(candidatura.getVaga().getEmpresa(), token);
		
		candidatura = CandidaturaMapper.update(candidatura,candidaturaDTO);
		
		save(candidatura);
	}
	
	private Candidatura findById(Integer id) {
		Candidatura candidatura = candidaturaRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
		
		return candidatura;
	}
	
	public void delete(Integer id, JwtAuthenticationToken token) {
		Candidatura candidatura = findById(id);
		
		tokenService.verifyEmpresaByToken(candidatura.getVaga().getEmpresa(), token);
		
		candidaturaRepository.delete(candidatura);
	}
	
	public List<Candidatura> findAll() {
		List<Candidatura> candidaturas = candidaturaRepository.findAll();
		
		return candidaturas;
	}
	
	public List<Candidatura> findByVagaId(Integer vagaId, JwtAuthenticationToken token) {
		Vaga vaga = vagaRepository.findById(vagaId).orElseThrow(() -> new IdNotFoundException());
		
		tokenService.verifyEmpresaByToken(vaga.getEmpresa(), token);
		
		List<Candidatura> candidaturas = new ArrayList<>();
		
		candidaturas = candidaturaRepository.findAllByVagaId(vagaId);
		
		return candidaturas;
	}
	
	

}
