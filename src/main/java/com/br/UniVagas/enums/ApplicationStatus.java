package com.br.UniVagas.enums;

public enum ApplicationStatus {
	ENVIADA("Enviada"),
	EM_ANALISE("Em análise"),
	FINALIZADA("Finalizada");
	
	private String status;
	
	private ApplicationStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
