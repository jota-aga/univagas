package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.EstudanteDTO;
import com.br.UniVagas.entity.Estudante;

public class EstudanteMapper {
	public static Estudante toEntity(EstudanteDTO dto) {
		Estudante estudante = new Estudante();
		estudante.setCpf(dto.cpf());
		estudante.setDataNascimento(dto.dataNascimento());
		estudante.setNome(dto.nome());
		estudante.setTelefone(dto.telefone());
		estudante.setDescricao(dto.descricao());
		
		return estudante;
	}
	
	public static Estudante atualizar(Estudante estudante, EstudanteDTO dto) {
		estudante.setCpf(dto.cpf());
		estudante.setDataNascimento(dto.dataNascimento());
		estudante.setNome(dto.nome());
		estudante.setTelefone(dto.telefone());
		estudante.setDescricao(dto.descricao());
		
		return estudante;
	}
}
