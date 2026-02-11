package com.br.UniVagas.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Legal name is required.")
	private String legalName;
	
	@CNPJ(message = "CNPJ invalid.")
	@NotEmpty(message = "CNPJ is required.")
	private String cnpj;
	
	private String description;
	
	private String location;
	
	private String sector;
	
	private String website;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vaga> vagas;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
}
