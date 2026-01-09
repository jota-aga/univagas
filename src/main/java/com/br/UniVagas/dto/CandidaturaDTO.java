package com.br.UniVagas.dto;

import java.time.LocalDate;

import com.br.UniVagas.enums.StatusDaCandidatura;

public record CandidaturaDTO(LocalDate dataAplicacao, StatusDaCandidatura statusDaCandidatura, Integer estudanteId, Integer vagaId) {}
