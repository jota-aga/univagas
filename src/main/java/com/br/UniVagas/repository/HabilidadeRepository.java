package com.br.UniVagas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Habilidade;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer>{
	public List<Habilidade> findAllByNomeContainsIgnoreCase(String nome);
	public Optional<Habilidade> findByNomeIgnoreCase(String nome);
}
