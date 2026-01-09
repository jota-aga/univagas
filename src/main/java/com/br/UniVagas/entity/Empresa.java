package com.br.UniVagas.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Empresa{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Razao Social is obrigatory.")
	private String razaoSocial;
	
	@CNPJ(message = "CNPJ invalid.")
	@NotEmpty(message = "CNPJ is obrigatory.")
	private String cnpj;
	
	private String descricao;
	
	private String localizacao;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vaga> vagas;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	public Empresa(String razaoSocial, String cnpj, String descricao, String localizacao) {
		super();
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.descricao = descricao;
		this.localizacao = localizacao;
	}

	public Empresa() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
