package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.CompanyDTO;
import com.br.UniVagas.entity.Company;

public class CompanyMapper {
	
	public static Company toEntity(CompanyDTO dto) {
		Company company = new Company();
		company.setCnpj(dto.cnpj());
		company.setLegalName(dto.razaoSocial());
		company.setDescription(dto.descricao());
		company.setLocation(dto.localizacao());
		company.setSector(dto.sector());
		company.setWebsite(dto.website());
		
		return company;
	}
	
	public static Company update(Company company, CompanyDTO dto) {
		company.setCnpj(dto.cnpj());
		company.setLegalName(dto.razaoSocial());
		company.setDescription(dto.descricao());
		company.setLocation(dto.localizacao());
		company.setSector(dto.sector());
		company.setWebsite(dto.website());
		
		return company;
	}
}
