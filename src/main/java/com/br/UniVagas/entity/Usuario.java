package com.br.UniVagas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
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
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Email(message = "Email is invalid.")
	@NotEmpty(message = "Email is required.")
	private String email;
	
	private String senha;
	
	@ManyToOne
	private Role role;

	public Usuario(String email, String senha, Role role) {
		this.email = email;
		this.senha = senha;
		this.role = role;
	}
}
