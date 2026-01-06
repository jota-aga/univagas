package com.br.UniVagas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.entity.Candidatura;
import com.br.UniVagas.entity.Empresa;
import com.br.UniVagas.repository.CandidaturaRepository;
import com.br.UniVagas.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	public List<Empresa> findAll(){
		return empresaRepository.findAll();
	}
	
	public void salvar(Empresa empresa) {
		Optional<Empresa> empresaRepetidaPeloCnpj = empresaRepository.findByCnpj(empresa.getCnpj());
		
		Optional<Empresa> empresaRepetidaPelaRazaoSocial = empresaRepository.findByRazaoSocialIgnoreCase(empresa.getCnpj());
		
		if(empresaRepetidaPelaRazaoSocial.isPresent()) {
			if(empresa.getId() == null) {
				return;
			}
			else if(empresa.getId() != empresaRepetidaPelaRazaoSocial.get().getId()) {
				return;
			}
		}
		
		if(empresaRepetidaPeloCnpj.isPresent()) {
			if(empresa.getId() == null) {
				return;
			}
			else if(empresa.getId() != empresaRepetidaPeloCnpj.get().getId()) {
				return;
			}
		}
	}
	
	public void excluirEmpresa(Empresa empresa) {
		
		empresaRepository.delete(empresa);
	}
}
