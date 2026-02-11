package com.br.UniVagas.enums;

public enum JobLevel {
	TRAINEE("Trainee"),
	ESTAGIO("Estágio"),
	VOLUNTARIO("Voluntário");
	
	private String tipo;
	
	JobLevel(String tipo){
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
