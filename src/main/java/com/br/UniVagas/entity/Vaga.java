package com.br.UniVagas.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.br.UniVagas.enums.StatusDaVaga;
import com.br.UniVagas.enums.TipoDeVaga;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vaga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String descricao;
	
	@Enumerated(value = EnumType.STRING)
	private TipoDeVaga tipoDeVaga;
	
	private LocalDate dataPublicacao;
	
	@Future
	private LocalDate dataLimite;
	
	@PositiveOrZero
	@Digits(fraction = 2, integer = 5)
	private BigDecimal bolsaSalario;
	
	@Positive
	private int cargaHoraria;
	
	private String localizacao;
	
	@Enumerated(value = EnumType.STRING)
	private StatusDaVaga status;
	
	@ManyToOne
	private Empresa Empresa;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Candidatura> candidaturas;

	public Vaga(String titulo, String descricao, TipoDeVaga tipoDeVaga,LocalDate dataPublicacao, LocalDate dataLimite, 
			BigDecimal bolsaSalario, int cargaHoraria,String localizacao, StatusDaVaga status, Empresa empresa,List<Candidatura> candidaturas) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.tipoDeVaga = tipoDeVaga;
		this.dataPublicacao = dataPublicacao;
		this.dataLimite = dataLimite;
		this.bolsaSalario = bolsaSalario;
		this.cargaHoraria = cargaHoraria;
		this.localizacao = localizacao;
		this.status = status;
		Empresa = empresa;
		this.candidaturas = candidaturas;
	}

	public Vaga() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeVaga getTipoDeVaga() {
		return tipoDeVaga;
	}

	public void setTipoDeVaga(TipoDeVaga tipoDeVaga) {
		this.tipoDeVaga = tipoDeVaga;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public LocalDate getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}

	public BigDecimal getBolsaSalario() {
		return bolsaSalario;
	}

	public void setBolsaSalario(BigDecimal bolsaSalario) {
		this.bolsaSalario = bolsaSalario;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public StatusDaVaga getStatus() {
		return status;
	}

	public void setStatus(StatusDaVaga status) {
		this.status = status;
	}

	public Empresa getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(Empresa empresa) {
		Empresa = empresa;
	}

	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}
	
	
}
