package com.br.UniVagas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.UniVagas.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Integer>{
	@Query("""
		    SELECT v
		    FROM Vaga v
		    WHERE LOWER(v.titulo) LIKE LOWER(CONCAT('%', :termo, '%'))
		       OR LOWER(v.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))
		""")
	List<Vaga> findAllByTituloOrDescricao(String termo);
}
