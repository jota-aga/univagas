package com.br.UniVagas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Integer>{
	Optional<Estudante> findByCpf(String cpf);
}
