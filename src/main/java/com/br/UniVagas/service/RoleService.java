package com.br.UniVagas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.UniVagas.entity.Role;
import com.br.UniVagas.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void saveRole(Role role) {
		roleRepository.save(role);
	}
	
	public Role findByName(String name) {
		Role role = roleRepository.findByNome(name).get();
		
		return role;
	}
}
