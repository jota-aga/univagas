package com.br.UniVagas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
	List<Application> findAllByCandidateId(Integer candidateId);
		
	List<Application> findAllByVagaId(Integer vaga_Id);
}
