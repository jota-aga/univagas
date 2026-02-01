package com.br.UniVagas.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Nome is required.")
	private String nome;
	
	@Past(message = "Data de Nascimento must to be in the past")
	@NotNull(message = "Data de Nascimento is required.")
	private LocalDate dataNascimento;
	
	private String telefone;
	
	private String descricao;
	
	@CPF(message = "CPF invalid.")
	private String cpf;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

}
