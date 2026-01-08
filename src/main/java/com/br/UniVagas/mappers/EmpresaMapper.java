package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.EmpresaDTO;
import com.br.UniVagas.entity.Empresa;

public class EmpresaMapper {
	
	public static Empresa toEntity(EmpresaDTO dto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(dto.cnpj());
		empresa.setRazaoSocial(dto.razaoSocial());
		empresa.setDescricao(dto.descricao());
		empresa.setLocalizacao(dto.localizacao());
		
		return empresa;
	}
	
	public static Empresa update(Empresa empresa, EmpresaDTO dto) {
		empresa.setCnpj(dto.cnpj());
		empresa.setRazaoSocial(dto.razaoSocial());
		empresa.setDescricao(dto.descricao());
		empresa.setLocalizacao(dto.localizacao());
		
		return empresa;
	}
}
