package com.br.UniVagas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Candidatura;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Integer>{
	List<Candidatura> findAllByEstudanteId(Integer estudanteId);
		
	List<Candidatura> findAllByVagaId(Integer vaga_Id);
}
