package com.br.UniVagas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.LoginDTO;
import com.br.UniVagas.entity.Role;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.exception.AlreadyExistsException;
import com.br.UniVagas.exception.EmailOrPasswordIncorrectException;
import com.br.UniVagas.repository.RoleRepository;
import com.br.UniVagas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public Usuario create(String email, String senha, String roleName) {
		verifyRepeatedEmail(email);
		
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		
		Role role = roleRepository.findByNome(roleName).orElseThrow(() -> new RuntimeException("Name of Role not found!"));
		usuario.setRole(role);
		
		String passwordEncrypted = bCryptPasswordEncoder.encode(senha);
		usuario.setSenha(passwordEncrypted);
		
		return usuario;
	}
	
	public String doLogin(LoginDTO loginDTO) {
				
		Optional<Usuario> optionalUsuario = userRepository.findByEmail(loginDTO.email());
		
		if(optionalUsuario.isEmpty() || !bCryptPasswordEncoder.matches(loginDTO.senha(), optionalUsuario.get().getSenha())) {
			throw new EmailOrPasswordIncorrectException();
		}
		
		return tokenService.generateToken(optionalUsuario.get());
	}
	
	private void verifyRepeatedEmail(String email) {
		Optional<Usuario> optionalUsuario = userRepository.findByEmail(email);
		
		if(optionalUsuario.isPresent()) {
			throw new AlreadyExistsException("Email");
		}
	}
}
