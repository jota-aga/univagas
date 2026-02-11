package com.br.UniVagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.CandidateDTO;
import com.br.UniVagas.entity.Application;
import com.br.UniVagas.entity.Candidate;
import com.br.UniVagas.entity.Role;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.exception.AlreadyExistsException;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.CandidateMapper;
import com.br.UniVagas.repository.ApplicationRepository;
import com.br.UniVagas.repository.CandidateRepository;

@Service
public class CandidateService {
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Candidate> findAll() {
		List<Candidate> candidates = candidateRepository.findAll();
		
		return candidates;
	}
	
	public void save(Candidate candidate) {
		Optional<Candidate> candidateRepetidoPeloCPF = candidateRepository.findByCpf(candidate.getCpf());
		
		if(candidateRepetidoPeloCPF.isPresent()) {
			if(candidate.getId() == null) {
				throw new AlreadyExistsException("CPF");
			}
			else if(candidate.getId() != candidateRepetidoPeloCPF.get().getId()) {
				throw new AlreadyExistsException("CPF");
			}
		}
		
		candidateRepository.save(candidate);
	}
	
	public void create(CandidateDTO candidateDTO) {
		Candidate candidate = CandidateMapper.toEntity(candidateDTO);
		
		Usuario usuario = usuarioService.create(candidateDTO.email(), candidateDTO.senha(), Role.Value.ESTUDANTE.name());
		
		candidate.setUsuario(usuario);
		
		save(candidate);
	}
	
	public Candidate findById(Integer id) {
		Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
		
		return optionalCandidate.orElseThrow(() -> new IdNotFoundException());
	}
	
	public void delete(Integer id) {
		Candidate candidate = findById(id);
		
		List<Application> applications = applicationRepository.findAllByCandidateId(candidate.getId());
		
		if(applications != null && !applications.isEmpty()) {
			applications.forEach(application -> application.setCandidate(null));
		}
		
		//verificar se quem vai deletar é o candidate
		
		applicationRepository.saveAll(applications);
		
		candidateRepository.delete(candidate);
	}
	
	public void update(Integer id, CandidateDTO dto) {
		Candidate candidate = findById(id);
		
		//verificar se quem vai editar é o candidate
		
		candidate = CandidateMapper.atualizar(candidate, dto);
		
		save(candidate);
	}
	
	public List<Candidate> findByDescricao(String descricao){
		List<Candidate> candidates = new ArrayList<>();
		
		candidates = candidateRepository.findAllByDescricaoContainingIgnoreCase(descricao);
		
		return candidates;
	}
}
