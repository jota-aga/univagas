package com.br.UniVagas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Empresa;
import com.br.UniVagas.entity.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Integer>{
	Optional<Estudante> findByCpf(String cpf);
	
	List<Estudante> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	Optional<Estudante> findByUsuarioId(Integer usuarioId);
}
