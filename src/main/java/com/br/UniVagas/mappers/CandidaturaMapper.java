package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.CandidaturaDTO;
import com.br.UniVagas.entity.Candidatura;

public class CandidaturaMapper {
	
	public static Candidatura toEntity(CandidaturaDTO dto) {
		Candidatura candidatura = new Candidatura();
		
		candidatura.setDataAplicacao(dto.dataAplicacao());
		candidatura.setStatusDaCandidatura(dto.statusDaCandidatura());
		
		 return candidatura;
	}
	
	public static Candidatura update(Candidatura candidatura, CandidaturaDTO dto) {
		
		candidatura.setDataAplicacao(dto.dataAplicacao());
		candidatura.setStatusDaCandidatura(dto.statusDaCandidatura());
		
		 return candidatura;
	}
		
}
