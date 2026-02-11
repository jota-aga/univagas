package com.br.UniVagas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Integer>{
	public List<Competence> findAllByNomeContainsIgnoreCase(String nome);
	public Optional<Competence> findByNomeIgnoreCase(String nome);
}
