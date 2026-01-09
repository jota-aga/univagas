package com.br.UniVagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.VagaDTO;
import com.br.UniVagas.entity.Empresa;
import com.br.UniVagas.entity.Vaga;
import com.br.UniVagas.mappers.VagaMapper;
import com.br.UniVagas.repository.VagaRepository;

@Service
public class VagaService {
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public void save(Vaga vaga) {		
		vagaRepository.save(vaga);
	}
	
	public void create(VagaDTO vagaDTO, JwtAuthenticationToken token) {
		Vaga vaga = VagaMapper.toEntity(vagaDTO);
		
		Empresa empresa = tokenService.findEmpresaByToken(token);
		
		vaga.setEmpresa(empresa);
		
		save(vaga);
	}
	
	public void delete(Integer id , JwtAuthenticationToken token) {
		Vaga vaga = findById(id);
		
		tokenService.verifyEmpresaByToken(vaga.getEmpresa(), token);
		
		vagaRepository.delete(vaga);
	}
	
	public List<Vaga> findAll(){
		return vagaRepository.findAll();
	}

	public void update(VagaDTO vagaDTO, Integer id, JwtAuthenticationToken token) {
		Vaga vaga = findById(id);
		
		tokenService.verifyEmpresaByToken(vaga.getEmpresa(), token);
		
		vaga = VagaMapper.update(vaga, vagaDTO);
		
		vagaRepository.save(vaga);
	}
	
	public List<Vaga> findByTermoDePesquisa(String termoDePesquisa) {
		List<Vaga> vagas = new ArrayList<>();
		
		vagas = vagaRepository.findAllByTituloOrDescricao(termoDePesquisa);
		
		return vagas;
	}
	
	public List<Vaga> findAllByEmpresa(JwtAuthenticationToken token) {
		Empresa empresa = tokenService.findEmpresaByToken(token);
		
		List<Vaga> vagas = vagaRepository.findAllByEmpresaId(empresa.getId());
		
		return vagas;
	}
	
	private Vaga findById(Integer id){
		Optional<Vaga> optionalVaga = vagaRepository.findById(id);
		
		Vaga vaga = optionalVaga.orElseThrow();
		
		return vaga;
	}

	
}
