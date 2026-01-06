package com.br.UniVagas.enums;

public enum TipoDeVaga {
	TRAINEE("Trainee"),
	ESTAGIO("Estágio"),
	VOLUNTARIO("Voluntário");
	
	private String tipo;
	
	TipoDeVaga(String tipo){
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
