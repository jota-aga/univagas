package com.br.UniVagas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
	List<Empresa> findAllByRazaoSocialContainingIgnoreCase(String razaoSocial);
	
	Optional<Empresa> findByRazaoSocialIgnoreCase(String razaoSocial);
	
	Optional<Empresa> findByCnpj(String cnpj);
	
	Optional<Empresa> findByUsuarioId(Integer usuarioId);
}
