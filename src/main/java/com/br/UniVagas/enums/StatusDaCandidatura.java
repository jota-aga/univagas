package com.br.UniVagas.enums;

public enum StatusDaCandidatura {
	ENVIADA("Enviada"),
	EM_ANALISE("Em análise"),
	FINALIZADA("Finalizada");
	
	private String status;
	
	private StatusDaCandidatura(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
