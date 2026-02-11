package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.CompetenceDTO;
import com.br.UniVagas.entity.Competence;

public class CompetenceMapper {
	
	public static Competence toEntity(CompetenceDTO competenceDTO) {
		Competence competence = new Competence();
		competence.setName(competenceDTO.nome());
		
		return competence;
	}
	
	public static CompetenceDTO toDTO(Competence competence) {
		CompetenceDTO dto = new CompetenceDTO(competence.getName());
		
		return dto;
	}
	
	public static Competence update(Competence competence, CompetenceDTO dto) {
		competence.setName(dto.nome());
		
		return competence;
	}
}
