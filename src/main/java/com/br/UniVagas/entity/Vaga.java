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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	private Company company;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Candidatura> candidaturas;
	
	@ManyToMany
	@JoinTable(name = "requer", joinColumns = @JoinColumn (name="id_vaga"), inverseJoinColumns = @JoinColumn(name="id_habilidade"))
	private List<Habilidade> habilidadeRequeridas;
}
