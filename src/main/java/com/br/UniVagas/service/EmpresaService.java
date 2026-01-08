package com.br.UniVagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.UniVagas.dto.EmpresaDTO;
import com.br.UniVagas.entity.Empresa;
import com.br.UniVagas.exception.CNPJAlreadyExistsException;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.exception.RazaoSocialAlreadyExistsException;
import com.br.UniVagas.mappers.EmpresaMapper;
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
	
	@Transactional
	public void save(Empresa empresa) {
		Optional<Empresa> empresaRepetidaPeloCnpj = empresaRepository.findByCnpj(empresa.getCnpj());
		
		Optional<Empresa> empresaRepetidaPelaRazaoSocial = empresaRepository.findByRazaoSocialIgnoreCase(empresa.getRazaoSocial());
		
		if(empresaRepetidaPelaRazaoSocial.isPresent()) {
			if(empresa.getId() == null) {
				throw new RazaoSocialAlreadyExistsException();
			}
			else if(empresa.getId() != empresaRepetidaPelaRazaoSocial.get().getId()) {
				throw new RazaoSocialAlreadyExistsException();
			}
		}
		
		if(empresaRepetidaPeloCnpj.isPresent()) {
			if(empresa.getId() == null) {
				throw new CNPJAlreadyExistsException();
			}
			else if(empresa.getId() != empresaRepetidaPeloCnpj.get().getId()) {
				throw new CNPJAlreadyExistsException();
			}
		}
		
		empresaRepository.save(empresa);
	}
	
	@Transactional
	public void delete(Integer id) throws Exception {
		Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
		
		Empresa empresa = optionalEmpresa.orElseThrow(() -> new IdNotFoundException());
		
		empresaRepository.delete(empresa);
	}
	
	public void update(Integer id, EmpresaDTO dto) {
		Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
		
		Empresa empresa = optionalEmpresa.orElseThrow(() -> new IdNotFoundException());
		
		empresa = EmpresaMapper.update(empresa, dto);
		
		save(empresa);
	}

	public List<Empresa> findAllByRazaoSocial(String razaoSocial) {
		List<Empresa> empresas = new ArrayList<>();
		
		empresas = empresaRepository.findAllByRazaoSocialContainingIgnoreCase(razaoSocial);
		
		return empresas;
	}
	
	
}
