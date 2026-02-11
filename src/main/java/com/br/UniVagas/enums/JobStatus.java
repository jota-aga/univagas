package com.br.UniVagas.enums;

public enum JobStatus {
	ABERTA("Aberta"),
	FECHADA("Fechada"),
	CANCELADA("Cancelada");
	
	private String status;

	JobStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
