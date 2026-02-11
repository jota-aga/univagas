package com.br.UniVagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.UniVagas.dto.CompanyDTO;
import com.br.UniVagas.entity.Company;
import com.br.UniVagas.entity.Role;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.exception.AlreadyExistsException;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.CompanyMapper;
import com.br.UniVagas.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	@Transactional
	public void save(Company company) {
		Optional<Company> companyRepetidaPeloCnpj = companyRepository.findByCnpj(company.getCnpj());
		
		Optional<Company> companyRepeatedByLegalName = companyRepository.findByLegalNameIgnoreCase(company.getLegalName());
		
		if(companyRepeatedByLegalName.isPresent()) {
			if(company.getId() == null) {
				throw new AlreadyExistsException("Legal Name");
			}
			else if(company.getId() != companyRepeatedByLegalName.get().getId()) {
				throw new AlreadyExistsException("Legal Name");
			}
		}
		
		if(companyRepetidaPeloCnpj.isPresent()) {
			if(company.getId() == null) {
				throw new AlreadyExistsException("CNPJ");
			}
			else if(company.getId() != companyRepetidaPeloCnpj.get().getId()) {
				throw new AlreadyExistsException("CNPJ");
			}
		}
		
		companyRepository.save(company);
	}
	
	public void update(Integer id, CompanyDTO dto) {
		
		Company company = findById(id);
		
		company = CompanyMapper.update(company, dto);
		
		save(company);
	}
	
	public void create(CompanyDTO dto) {
		Company company = CompanyMapper.toEntity(dto);
		
		Usuario usuario = usuarioService.create(dto.email(), dto.senha(), Role.Value.EMPRESA.name());
		
		company.setUsuario(usuario);
		
		save(company);
	}
	
	@Transactional
	public void delete(Integer id) throws Exception {
		Company company = findById(id);
		
		companyRepository.delete(company);
	}

	public List<Company> findAllByLegalName(String legalName) {
		List<Company> companys = new ArrayList<>();
		
		companys = companyRepository.findAllByLegalNameContainingIgnoreCase(legalName);
		
		return companys;
	}
	
	public Company findById(Integer id) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		
		return optionalCompany.orElseThrow(() -> new IdNotFoundException());
	}
}
