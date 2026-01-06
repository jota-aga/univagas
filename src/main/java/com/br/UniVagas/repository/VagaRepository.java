package com.br.UniVagas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Integer>{
	List<Vaga> findAllByTituloContainingIgnoreCase(String titulo);
	List<Vaga> findAllByDescricaoContainingIgnoreCase(String descricao);
}
