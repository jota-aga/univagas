package com.br.UniVagas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.UniVagas.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByNome(String nome);
}
