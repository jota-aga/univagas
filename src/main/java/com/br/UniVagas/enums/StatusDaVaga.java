package com.br.UniVagas.enums;

public enum StatusDaVaga {
	ABERTA("Aberta"),
	FECHADA("Fechada");
	
	private String status;

	StatusDaVaga(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
