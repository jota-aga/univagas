package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.HabilidadeDTO;
import com.br.UniVagas.entity.Habilidade;

public class HabilidadeMapper {
	
	public static Habilidade toEntity(HabilidadeDTO habilidadeDTO) {
		Habilidade habilidade = new Habilidade();
		habilidade.setNome(habilidadeDTO.nome());
		
		return habilidade;
	}
	
	public static HabilidadeDTO toDTO(Habilidade habilidade) {
		HabilidadeDTO dto = new HabilidadeDTO(habilidade.getNome());
		
		return dto;
	}
	
	public static Habilidade update(Habilidade habilidade, HabilidadeDTO dto) {
		habilidade.setNome(dto.nome());
		
		return habilidade;
	}
}
