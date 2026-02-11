package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.ApplicationDTO;
import com.br.UniVagas.entity.Application;

public class ApplicationMapper {
	
	public static Application toEntity(ApplicationDTO dto) {
		Application application = new Application();
		
		application.setDataAplicacao(dto.dataAplicacao());
		application.setStatus(dto.status());
		
		 return application;
	}
	
	public static Application update(Application application, ApplicationDTO dto) {
		
		application.setDataAplicacao(dto.dataAplicacao());
		application.setStatus(dto.status());
		
		 return application;
	}
		
}
