package com.br.UniVagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.EstudanteDTO;
import com.br.UniVagas.entity.Candidatura;
import com.br.UniVagas.entity.Estudante;
import com.br.UniVagas.entity.Role;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.exception.AlreadyExistsException;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.EstudanteMapper;
import com.br.UniVagas.repository.CandidaturaRepository;
import com.br.UniVagas.repository.EstudanteRepository;

@Service
public class EstudanteService {
	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Estudante> findAll() {
		List<Estudante> estudantes = estudanteRepository.findAll();
		
		return estudantes;
	}
	
	public void save(Estudante estudante) {
		Optional<Estudante> estudanteRepetidoPeloCPF = estudanteRepository.findByCpf(estudante.getCpf());
		
		if(estudanteRepetidoPeloCPF.isPresent()) {
			if(estudante.getId() == null) {
				throw new AlreadyExistsException("CPF");
			}
			else if(estudante.getId() != estudanteRepetidoPeloCPF.get().getId()) {
				throw new AlreadyExistsException("CPF");
			}
		}
		
		estudanteRepository.save(estudante);
	}
	
	public void create(EstudanteDTO estudanteDTO) {
		Estudante estudante = EstudanteMapper.toEntity(estudanteDTO);
		
		Usuario usuario = usuarioService.create(estudanteDTO.email(), estudanteDTO.senha(), Role.Value.ESTUDANTE.name());
		
		estudante.setUsuario(usuario);
		
		save(estudante);
	}
	
	public Estudante findById(Integer id) {
		Optional<Estudante> optionalEstudante = estudanteRepository.findById(id);
		
		return optionalEstudante.orElseThrow(() -> new IdNotFoundException());
	}
	
	public void delete(Integer id) {
		Estudante estudante = findById(id);
		
		List<Candidatura> candidaturas = candidaturaRepository.findAllByEstudanteId(estudante.getId());
		
		if(candidaturas != null && !candidaturas.isEmpty()) {
			candidaturas.forEach(candidatura -> candidatura.setEstudante(null));
		}
		
		//verificar se quem vai deletar é o estudante
		
		candidaturaRepository.saveAll(candidaturas);
		
		estudanteRepository.delete(estudante);
	}
	
	public void update(Integer id, EstudanteDTO dto) {
		Estudante estudante = findById(id);
		
		//verificar se quem vai editar é o estudante
		
		estudante = EstudanteMapper.atualizar(estudante, dto);
		
		save(estudante);
	}
	
	public List<Estudante> findByDescricao(String descricao){
		List<Estudante> estudantes = new ArrayList<>();
		
		estudantes = estudanteRepository.findAllByDescricaoContainingIgnoreCase(descricao);
		
		return estudantes;
	}
}
