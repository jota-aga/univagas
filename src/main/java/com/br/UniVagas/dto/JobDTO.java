package com.br.UniVagas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.br.UniVagas.enums.JobLevel;
import com.br.UniVagas.enums.JobStatus;

public record JobDTO(String titulo, String descricao, JobLevel tipoDeJob, LocalDate dataPublicacao, LocalDate dataLimite,
BigDecimal bolsaSalario, int cargaHoraria, String localizacao, JobStatus status, Integer companyId) {}
