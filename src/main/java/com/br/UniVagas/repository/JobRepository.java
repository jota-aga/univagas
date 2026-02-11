package com.br.UniVagas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.UniVagas.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{
	@Query("""
		    SELECT v
		    FROM Job v
		    WHERE LOWER(v.titulo) LIKE LOWER(CONCAT('%', :termo, '%'))
		       OR LOWER(v.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))
		""")
	List<Job> findAllByTituloOrDescricao(String termo);
	
	List<Job> findAllByCompanyId(Integer companyId);
}
