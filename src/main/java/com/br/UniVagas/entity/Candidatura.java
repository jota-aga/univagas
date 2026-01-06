package com.br.UniVagas.entity;

import java.time.LocalDate;

import com.br.UniVagas.enums.StatusDaCandidatura;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;

@Entity
public class Candidatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@FutureOrPresent
	private LocalDate dataAplicacao;
	
	@Enumerated(value = EnumType.STRING)
	private StatusDaCandidatura statusDaCandidatura;
	
	@ManyToOne
	private Estudante estudante;
	
	@ManyToOne
	private Vaga vaga;

	public Candidatura(@FutureOrPresent LocalDate dataAplicacao, StatusDaCandidatura statusDaCandidatura,
			Estudante estudante, Vaga vaga) {
		super();
		this.dataAplicacao = dataAplicacao;
		this.statusDaCandidatura = statusDaCandidatura;
		this.estudante = estudante;
		this.vaga = vaga;
	}

	public Candidatura() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public StatusDaCandidatura getStatusDaCandidatura() {
		return statusDaCandidatura;
	}

	public void setStatusDaCandidatura(StatusDaCandidatura statusDaCandidatura) {
		this.statusDaCandidatura = statusDaCandidatura;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
}
