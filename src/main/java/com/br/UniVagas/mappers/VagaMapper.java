package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.VagaDTO;
import com.br.UniVagas.entity.Vaga;

public class VagaMapper {
	public static Vaga toEntity(VagaDTO dto) {
		Vaga vaga = new Vaga();
		
		vaga.setBolsaSalario(dto.bolsaSalario());
		vaga.setCargaHoraria(dto.cargaHoraria());
		vaga.setDataLimite(dto.dataLimite());
		vaga.setDataPublicacao(dto.dataPublicacao());
		vaga.setDescricao(dto.descricao());
		vaga.setLocalizacao(dto.localizacao());
		vaga.setStatus(dto.status());
		vaga.setTipoDeVaga(dto.tipoDeVaga());
		vaga.setTitulo(dto.titulo());
		
		return vaga;
	}
	
	public static Vaga update(Vaga vaga, VagaDTO dto) {	
		
		vaga.setBolsaSalario(dto.bolsaSalario());
		vaga.setCargaHoraria(dto.cargaHoraria());
		vaga.setDataLimite(dto.dataLimite());
		vaga.setDataPublicacao(dto.dataPublicacao());
		vaga.setDescricao(dto.descricao());
		vaga.setLocalizacao(dto.localizacao());
		vaga.setStatus(dto.status());
		vaga.setTipoDeVaga(dto.tipoDeVaga());
		vaga.setTitulo(dto.titulo());
		
		return vaga;
	}
}
