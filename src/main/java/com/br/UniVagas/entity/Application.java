package com.br.UniVagas.entity;

import java.time.LocalDate;

import com.br.UniVagas.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@PastOrPresent(message = "Data de Aplicação cant be in the future.")
	private LocalDate dataAplicacao;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Status da Application is required.")
	private ApplicationStatus status;
	
	@ManyToOne
	@NotNull(message = "Candidate is required.")
	private Candidate candidate;
	
	@ManyToOne
	@NotNull(message = "Job is required.")
	private Job job;
}
