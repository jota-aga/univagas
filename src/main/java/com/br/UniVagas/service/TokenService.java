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

import com.br.UniVagas.entity.Company;
import com.br.UniVagas.entity.Candidate;
import com.br.UniVagas.entity.Usuario;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.repository.CompanyRepository;
import com.br.UniVagas.repository.CandidateRepository;

@Service
public class TokenService {
	
	@Autowired
	private JwtEncoder jwtEncoder;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
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
	
	public Candidate findCandidateByToken(JwtAuthenticationToken token) {
		Integer usuarioId = Integer.valueOf(token.getToken().getSubject());
		
		Optional<Candidate> optionalCandidate = candidateRepository.findByUsuarioId(usuarioId);
		
		Candidate candidate = optionalCandidate.orElseThrow(() -> new IdNotFoundException());
		
		return candidate;
	}
	
	public Company findCompanyByToken(JwtAuthenticationToken token) {
		Integer companyId = Integer.valueOf(token.getToken().getSubject());
		
		Optional<Company> optionalCompany = companyRepository.findByUsuarioId(companyId);
		
		Company company = optionalCompany.orElseThrow(() -> new IdNotFoundException());
		
		return company;
	}
	
	public void verifyCandidateByToken(Candidate candidate, JwtAuthenticationToken token) {
		Candidate candidateToken = findCandidateByToken(token);
		
		if(!candidateToken.equals(candidate)) {
			throw new RuntimeException("This Candidatura isnt yours!");
		}
	}
	
	public void verifyCompanyByToken(Company company, JwtAuthenticationToken token) {
		Company companyToken = findCompanyByToken(token);
		
		if(!companyToken.equals(company)) {
			throw new RuntimeException("This Candidatura isnt yours!");
		}
	}
}
