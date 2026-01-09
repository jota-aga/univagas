package com.br.UniVagas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.UniVagas.entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByEmail(String email);
	
	@Query("select u from Usuario u where u.role.nome = :roleNome")
	Optional<Usuario> findByRole(String roleNome);
	
}
