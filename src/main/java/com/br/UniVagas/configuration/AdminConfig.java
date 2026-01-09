package com.br.UniVagas.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.br.UniVagas.entity.Role;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.repository.RoleRepository;
import com.br.UniVagas.repository.UsuarioRepository;

@Configuration
public class AdminConfig implements CommandLineRunner{
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		Optional<Usuario> optionalUsuario = userRepository.findByRole(Role.Value.ADMIN.name());
		
		optionalUsuario.ifPresentOrElse(u -> System.out.println("Usuário Admin Já existe no banco."), 
										() -> {
											Role role = roleRepository.findByNome(Role.Value.ADMIN.name()).get();
											Usuario usuario = new Usuario("admin@email.com", "123456789", role);
											
											userRepository.save(usuario);
										});
	}

}
