package com.br.UniVagas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.br.UniVagas.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	Optional<Candidate> findByCpf(String cpf);
	
	List<Candidate> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	Optional<Candidate> findByUsuarioId(Integer usuarioId);
}
