package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.CandidateDTO;
import com.br.UniVagas.entity.Candidate;

public class CandidateMapper {
	public static Candidate toEntity(CandidateDTO dto) {
		Candidate candidate = new Candidate();
		candidate.setCpf(dto.cpf());
		candidate.setBirthday(dto.dataNascimento());
		candidate.setName(dto.nome());
		candidate.setPhone(dto.telefone());
		
		return candidate;
	}
	
	public static Candidate atualizar(Candidate candidate, CandidateDTO dto) {
		candidate.setCpf(dto.cpf());
		candidate.setBirthday(dto.dataNascimento());
		candidate.setName(dto.nome());
		candidate.setPhone(dto.telefone());
		
		return candidate;
	}
}
