package com.br.UniVagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.UniVagas.dto.EmpresaDTO;
import com.br.UniVagas.entity.Empresa;
import com.br.UniVagas.entity.Role;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.exception.AlreadyExistsException;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.EmpresaMapper;
import com.br.UniVagas.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Empresa> findAll(){
		return empresaRepository.findAll();
	}
	
	@Transactional
	public void save(Empresa empresa) {
		Optional<Empresa> empresaRepetidaPeloCnpj = empresaRepository.findByCnpj(empresa.getCnpj());
		
		Optional<Empresa> empresaRepetidaPelaRazaoSocial = empresaRepository.findByRazaoSocialIgnoreCase(empresa.getRazaoSocial());
		
		if(empresaRepetidaPelaRazaoSocial.isPresent()) {
			if(empresa.getId() == null) {
				throw new AlreadyExistsException("Razão Social");
			}
			else if(empresa.getId() != empresaRepetidaPelaRazaoSocial.get().getId()) {
				throw new AlreadyExistsException("Razão Social");
			}
		}
		
		if(empresaRepetidaPeloCnpj.isPresent()) {
			if(empresa.getId() == null) {
				throw new AlreadyExistsException("CNPJ");
			}
			else if(empresa.getId() != empresaRepetidaPeloCnpj.get().getId()) {
				throw new AlreadyExistsException("CNPJ");
			}
		}
		
		empresaRepository.save(empresa);
	}
	
	public void update(Integer id, EmpresaDTO dto) {
		
		Empresa empresa = findById(id);
		
		empresa = EmpresaMapper.update(empresa, dto);
		
		save(empresa);
	}
	
	public void create(EmpresaDTO dto) {
		Empresa empresa = EmpresaMapper.toEntity(dto);
		
		Usuario usuario = usuarioService.create(dto.email(), dto.senha(), Role.Value.EMPRESA.name());
		
		empresa.setUsuario(usuario);
		
		save(empresa);
	}
	
	@Transactional
	public void delete(Integer id) throws Exception {
		Empresa empresa = findById(id);
		
		empresaRepository.delete(empresa);
	}

	public List<Empresa> findAllByRazaoSocial(String razaoSocial) {
		List<Empresa> empresas = new ArrayList<>();
		
		empresas = empresaRepository.findAllByRazaoSocialContainingIgnoreCase(razaoSocial);
		
		return empresas;
	}
	
	public Empresa findById(Integer id) {
		Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
		
		return optionalEmpresa.orElseThrow(() -> new IdNotFoundException());
	}
}
