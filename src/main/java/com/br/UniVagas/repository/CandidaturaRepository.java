package com.br.UniVagas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Candidatura;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Integer>{
	List<Candidatura> findAllByCandidateId(Integer candidateId);
		
	List<Candidatura> findAllByVagaId(Integer vaga_Id);
}
