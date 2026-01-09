package com.br.UniVagas.configuration;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.br.UniVagas.entity.Role;
import com.br.UniVagas.repository.RoleRepository;


@Configuration
@Order(1)
public class RoleConfig implements CommandLineRunner{
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Optional<Role> optionalRole = roleRepository.findByNome(Role.Value.ADMIN.name());
		
		optionalRole.ifPresentOrElse(u -> System.out.println("As Roles já estão no banco!"),
				() ->{
					Role admin = new Role(Role.Value.ADMIN.name());
					Role empresa = new Role(Role.Value.EMPRESA.name());
					Role estudante = new Role(Role.Value.ESTUDANTE.name());
					
					roleRepository.saveAll(List.of(admin, empresa, estudante));
				});
	}

}
