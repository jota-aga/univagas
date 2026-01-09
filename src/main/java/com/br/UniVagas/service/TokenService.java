package com.br.UniVagas.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.UniVagas.entity.Empresa;
import com.br.UniVagas.entity.Estudante;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.repository.EmpresaRepository;
import com.br.UniVagas.repository.EstudanteRepository;

@Service
public class TokenService {
	
	@Autowired
	private JwtEncoder jwtEncoder;
	
	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public String generateToken(Usuario usuario) {
		var scope = usuario.getRole().getNome();
				
		var claims = JwtClaimsSet.builder()
					.issuer("issuer")
					.subject(usuario.getId().toString())
					.issuedAt(Instant.now())
					.expiresAt(tokenExpiration())
					.claim("scope", scope)
					.build();
		
		var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		
		return jwtValue;
	}
	
	public Instant tokenExpiration() {
		return LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00"));
	}
	
	public Estudante findEstudanteByToken(JwtAuthenticationToken token) {
		Integer usuarioId = Integer.valueOf(token.getToken().getSubject());
		
		Optional<Estudante> optionalEstudante = estudanteRepository.findByUsuarioId(usuarioId);
		
		Estudante estudante = optionalEstudante.orElseThrow(() -> new IdNotFoundException());
		
		return estudante;
	}
	
	public Empresa findEmpresaByToken(JwtAuthenticationToken token) {
		Integer empresaId = Integer.valueOf(token.getToken().getSubject());
		
		Optional<Empresa> optionalEmpresa = empresaRepository.findByUsuarioId(empresaId);
		
		Empresa empresa = optionalEmpresa.orElseThrow(() -> new IdNotFoundException());
		
		return empresa;
	}
	
	public void verifyEstudanteByToken(Estudante estudante, JwtAuthenticationToken token) {
		Estudante estudanteToken = findEstudanteByToken(token);
		
		if(estudanteToken.equals(estudante)) {
			throw new RuntimeException("This Candidatura isnt yours!");
		}
	}
	
	public void verifyEmpresaByToken(Empresa empresa, JwtAuthenticationToken token) {
		Empresa empresaToken = findEmpresaByToken(token);
		
		if(empresaToken.equals(empresa)) {
			throw new RuntimeException("This Candidatura isnt yours!");
		}
	}
}
