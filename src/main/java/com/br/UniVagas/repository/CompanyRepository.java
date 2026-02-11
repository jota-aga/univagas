package com.br.UniVagas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
	List<Company> findAllByLegalNameContainingIgnoreCase(String legalName);
	
	Optional<Company> findByLegalNameIgnoreCase(String legalName);
	
	Optional<Company> findByCnpj(String cnpj);
	
	Optional<Company> findByUsuarioId(Integer usuarioId);
}
